package dev.sirosh.raskatbackend.driver.docker.actuator;

import dev.sirosh.raskatbackend.driver.base.actuator.AppActuator;
import dev.sirosh.raskatbackend.driver.docker.client.DockerClient;
import dev.sirosh.raskatbackend.driver.docker.dto.response.DockerCreateResponseDto;
import dev.sirosh.raskatbackend.driver.docker.mapper.DockerRequestMapper;
import dev.sirosh.raskatbackend.entity.App;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DockerAppActuator implements AppActuator {
    private final DockerClient dockerClient;
    private final DockerRequestMapper dockerRequestMapper;

    @Override
    public List<App> modify(App app) throws IOException {
        DockerCreateResponseDto container = dockerClient.containerCreate(dockerRequestMapper.toDockerCreateConfig(app), app.getEnvironment().getConfig());
//        app.getContainer().setContainerId(container.getId());
        return null;
    }

    @Override
    public boolean stop(App app) throws IOException {
        return dockerClient.containerStop(app.getContainer().get(0).getContainerId(), app.getEnvironment().getConfig());
    }

    @Override
    public boolean start(App app) throws IOException {
        return dockerClient.containerStart(app.getContainer().get(0).getContainerId(), app.getEnvironment().getConfig());
    }

    @Override
    public boolean delete(App app) throws IOException {
        return dockerClient.containerDelete(app.getContainer().get(0).getContainerId(), app.getEnvironment().getConfig());
    }
}
