package dev.sirosh.raskatbackend.driver.base;

import dev.sirosh.raskatbackend.driver.base.actuator.AppActuator;
import dev.sirosh.raskatbackend.driver.base.actuator.EnvironmentActuator;

public interface Driver {
    AppActuator getAppActuator();

    EnvironmentActuator getEnvironmentActuator();

    /**
     * edit logic if Plugin class is not in the root of yours package
     *
     * @return base package for further entity class scan
     */
    default String getDriverName() {
        return this.getClass().getPackageName();
    }
}
