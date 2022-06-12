package dev.sirosh.raskatbackend.driver.base.actuator;

import dev.sirosh.raskatbackend.entity.App;
import dev.sirosh.raskatbackend.entity.EnvironmentConfig;

import java.io.IOException;

public interface AppActuator {
    App modify(App app, EnvironmentConfig environment) throws IOException;

    boolean stop(App app, EnvironmentConfig environment) throws IOException;

    boolean start(App app, EnvironmentConfig environment) throws IOException;

    boolean delete(App app, EnvironmentConfig environment) throws IOException;
}
