package dev.sirosh.raskatbackend.service;

import dev.sirosh.raskatbackend.driver.DriverHolder;
import dev.sirosh.raskatbackend.driver.base.actuator.AppActuator;
import dev.sirosh.raskatbackend.dto.AppDto;
import dev.sirosh.raskatbackend.dto.Result;
import dev.sirosh.raskatbackend.entity.App;
import dev.sirosh.raskatbackend.entity.Environment;
import dev.sirosh.raskatbackend.mapper.AppMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

// TODO: 31.05.2022 handle actuators response
// TODO: 31.05.2022 add synchronization

/**
 * this class deal with database interaction and actuators form drivers to start apps in particular environments
 */
@Service
@RequiredArgsConstructor
public class AppServiceImpl implements AppService {
    private final AppMapper appMapper;
    private final DriverHolder driverHolder;
    private final EnvironmentService environmentService;
    private final MongoTemplate mongoTemplate;

    /**
     * method to determine driver and get app actuator
     *
     * @param env - environment entity
     * @return app actuator of needed driver
     */
    private AppActuator getActuator(Environment env) {
        return driverHolder.getDriver(env).getAppActuator();
    }

    /**
     * updates app in environment
     *
     * @param environment environment name
     * @param app         app entity
     */
    private void updateApp(String environment, App app) {
        mongoTemplate.updateMulti(new Query(Criteria.where("_id").is(environment)),
                new Update().push("apps", app),
                Environment.class);
    }

    /**
     * saving app in db and create/start app in environment
     *
     * @param environment environment name
     * @param appDto      app dto
     * @return result from driver
     * @throws IOException
     */
    @Override
    public Result<List<AppDto>> create(String environment, List<AppDto> appDto) throws IOException {
        Environment environmentEntity = environmentService.getEntity(environment);
        AppActuator actuator = getActuator(environmentEntity);

        List<AppDto> res = appDto.stream().map(a -> {
            App app = appMapper.toEntity(a);
            try {
                actuator.modify(app);
                actuator.start(app);
            } catch (IOException e) {
                e.printStackTrace();
            }
            updateApp(environment, app);
            return appMapper.toDto(app);
        }).collect(Collectors.toList());
        return Result.successResult(res);
    }

    /**
     * delete app from environment and db
     *
     * @param environment environment name
     * @param name        app name
     * @return result from driver
     * @throws IOException
     */
    @Override
    public Result<Void> delete(String environment, String name) throws IOException {
        Environment environmentEntity = environmentService.getEntity(environment);
        App app = environmentEntity.getApps().stream()
                .filter(a -> a.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("can't find app " + name + " in environment " + environment));
        boolean success = getActuator(environmentEntity).delete(app);
        mongoTemplate.updateMulti(new Query(Criteria.where("_id").is(environment)),
                new Update().pull("apps", name),
                Environment.class);
        return Result.successResult();
    }

    /**
     * get app from db
     *
     * @param environment environment name
     * @param name        app name
     * @return app form db
     * @throws IOException
     */
    @Override
    public Result<AppDto> getDto(String environment, String name) {
        Environment environmentEntity = environmentService.getEntity(environment);
        App app = environmentEntity.getApps().stream()
                .filter(a -> a.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("can't find app " + name + " in environment " + environment));
        return Result.successResult(appMapper.toDto(app));
    }

    /**
     * get apps from db
     *
     * @param environment environment name
     * @return apps form db
     * @throws IOException
     */
    @Override
    public Result<List<AppDto>> getAll(String environment) {
        Environment environmentEntity = environmentService.getEntity(environment);
        return Result.successResult(appMapper.toDto(environmentEntity.getApps()));
    }

    /**
     * start app
     *
     * @param environment environment name
     * @param name        app name
     * @return app form db
     * @throws IOException
     */
    @Override
    public Result<Void> start(String environment, String name) throws IOException {
        Environment environmentEntity = environmentService.getEntity(environment);
        App app = environmentEntity.getApps().stream()
                .filter(a -> a.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("can't find app " + name + " in environment " + environment));
        getActuator(environmentEntity).start(app);  // TODO: 31.05.2022 save state
        updateApp(environment, app);
        return Result.successResult();
    }

    /**
     * start app
     *
     * @param environment environment name
     * @param name        app name
     * @return app form db
     * @throws IOException
     */
    @Override
    public Result<Void> stop(String environment, String name) throws IOException {
        Environment environmentEntity = environmentService.getEntity(environment);
        App app = environmentEntity.getApps().stream()
                .filter(a -> a.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("can't find app " + name + " in environment " + environment));
        getActuator(environmentEntity).stop(app); // TODO: 31.05.2022 save state
        updateApp(environment, app);
        return Result.successResult();
    }
}
