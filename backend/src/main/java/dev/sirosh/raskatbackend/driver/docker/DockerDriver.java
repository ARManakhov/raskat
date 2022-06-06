package dev.sirosh.raskatbackend.driver.docker;

import dev.sirosh.raskatbackend.driver.base.Driver;
import dev.sirosh.raskatbackend.driver.docker.actuator.DockerAppActuator;
import dev.sirosh.raskatbackend.driver.docker.actuator.DockerEnvironmentActuator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor
public class DockerDriver implements Driver {
    private final DockerAppActuator appActuator;
    private final DockerEnvironmentActuator environmentActuator;
}
