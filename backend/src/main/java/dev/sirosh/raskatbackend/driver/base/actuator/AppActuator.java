package dev.sirosh.raskatbackend.driver.base.actuator;

import dev.sirosh.raskatbackend.entity.App;

import java.io.IOException;
import java.util.List;

public interface AppActuator {
    List<App> modify(App app) throws IOException;

    boolean stop(App app) throws IOException;

    boolean start(App app) throws IOException;

    boolean delete(App app) throws IOException;
}
