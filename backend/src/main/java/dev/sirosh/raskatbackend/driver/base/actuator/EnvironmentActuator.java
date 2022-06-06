package dev.sirosh.raskatbackend.driver.base.actuator;

import dev.sirosh.raskatbackend.entity.App;
import dev.sirosh.raskatbackend.entity.EnvironmentConfig;

import java.io.IOException;
import java.util.List;

public interface EnvironmentActuator {
    boolean ping(EnvironmentConfig environment) throws IOException;

    List<App> scrapeContainers(EnvironmentConfig environment) throws IOException;
}
