package dev.sirosh.raskatbackend.service;

import dev.sirosh.raskatbackend.driver.DriverHolder;
import dev.sirosh.raskatbackend.driver.base.actuator.EnvironmentActuator;
import dev.sirosh.raskatbackend.dto.EnvironmentDto;
import dev.sirosh.raskatbackend.entity.Environment;
import dev.sirosh.raskatbackend.mapper.AppMapper;
import dev.sirosh.raskatbackend.mapper.EnvironmentMapper;
import dev.sirosh.raskatbackend.repository.EnvironmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnvironmentServiceImpl implements EnvironmentService {
    private final EnvironmentRepository environmentRepository;
    public final EnvironmentMapper environmentMapper;
    private final DriverHolder driverHolder;
    private final AppMapper appMapper;

    private EnvironmentActuator getActuator(Environment environment) {
        return driverHolder.getDriver(environment).getEnvironmentActuator();
    }

    // TODO: 19.05.2022 add filters
    @Override
    public List<EnvironmentDto> getAll() {
        return environmentMapper.toDto(environmentRepository.findAll());
    }

    // TODO: 19.05.2022 add check availability before return results
    @Override
    public EnvironmentDto getDto(String name) {
        return environmentMapper.toDto(getEntity(name));
    }

    @Override
    public Environment getEntity(String name) {
        return environmentRepository.findById(name)
                .orElseThrow(() -> new IllegalArgumentException("can't find environment by name " + name));
    }

    // TODO: 19.05.2022 on create get all existing containers/services/pods or else
    @Override
    public void saveOrUpdate(EnvironmentDto environmentDto) throws IOException {
        Environment environment = environmentMapper.toEntity(environmentDto);
        environmentRepository.save(environment);
    }

    @Override
    public boolean delete(String environment) throws IOException {
        environmentRepository.deleteById(environment);
        return true;
    }

    @Override
    public boolean ping(String environment) throws IOException {
        Environment environmentDto = getEntity(environment);
        return getActuator(environmentDto).ping(environmentDto.getConfig());
    }

    // TODO: 23.05.2022 run on start
    // TODO: 23.05.2022 run on env create
    // FIXME: 23.05.2022 add only not existed apps
    // TODO: 23.05.2022 save to database
    @Override
    public EnvironmentDto scrape(String name) throws IOException {
        Environment environment = getEntity(name);
        environment.getApps().addAll(getActuator(environment).scrapeContainers(environment.getConfig()));
        return environmentMapper.toDto(environment);
    }
}
