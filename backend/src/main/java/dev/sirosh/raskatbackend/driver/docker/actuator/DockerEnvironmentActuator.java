package dev.sirosh.raskatbackend.driver.docker.actuator;

import dev.sirosh.raskatbackend.driver.base.actuator.EnvironmentActuator;
import dev.sirosh.raskatbackend.driver.docker.client.DockerClient;
import dev.sirosh.raskatbackend.driver.docker.mapper.DockerRequestMapper;
import dev.sirosh.raskatbackend.entity.App;
import dev.sirosh.raskatbackend.entity.EnvironmentConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DockerEnvironmentActuator implements EnvironmentActuator {
    private final DockerClient dockerClient;
    private final DockerRequestMapper dockerRequestMapper;

    @Override
    public boolean ping(EnvironmentConfig environment) throws IOException {
        return dockerClient.ping(environment);
    }

    @Override
    public List<App> scrapeContainers(EnvironmentConfig environment) throws IOException {
        return dockerRequestMapper.containerToCommonConfig(dockerClient.containersList(environment));
    }
}
