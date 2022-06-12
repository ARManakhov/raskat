package dev.sirosh.raskatbackend.driver.docker.actuator;

import dev.sirosh.raskatbackend.driver.base.actuator.AppActuator;
import dev.sirosh.raskatbackend.driver.docker.client.DockerClient;
import dev.sirosh.raskatbackend.driver.docker.mapper.DockerRequestMapper;
import dev.sirosh.raskatbackend.entity.App;
import dev.sirosh.raskatbackend.entity.Container;
import dev.sirosh.raskatbackend.entity.EnvironmentConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class DockerAppActuator implements AppActuator {
    private final DockerClient dockerClient;
    private final DockerRequestMapper dockerRequestMapper;

    // TODO: 11.06.2022 net and volumes mounts
    @Override
    public App modify(App app, EnvironmentConfig environment) throws IOException {
        for (Container c : app.getContainer()) {
            dockerClient.containerCreate(c.getName(), dockerRequestMapper.toCreateRequestDto(c), environment);
            //            c.setId(dockerCreateResponseDto.getId());
        }
        return app;
    }

    @Override
    public boolean stop(App app, EnvironmentConfig environment) throws IOException {
        for (Container c : app.getContainer()) {
            dockerClient.containerStop(c.getName(), environment);
        }
        return true;
    }

    @Override
    public boolean start(App app, EnvironmentConfig environment) throws IOException {
        for (Container c : app.getContainer()) {
            dockerClient.containerStart(c.getName(), environment);
        }
        return true;
    }

    @Override
    public boolean delete(App app, EnvironmentConfig environment) throws IOException {
        for (Container c : app.getContainer()) {
            dockerClient.containerDelete(c.getName(), environment);
        }
        return false;
    }
}
