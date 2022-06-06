package dev.sirosh.raskatbackend.driver;

import dev.sirosh.raskatbackend.driver.base.Driver;
import dev.sirosh.raskatbackend.entity.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class DriverHolder {
    private final Map<String, Driver> drivers;

    @Autowired(required = false)
    public DriverHolder(Set<Driver> drivers) {
        this.drivers = drivers.stream().collect(Collectors.toMap(Driver::getDriverName, Function.identity()));
    }

    public void registerDriver(Driver driver) {
        drivers.put(driver.getDriverName(), driver);
    }

    public Driver getDriver(Environment env) {
        Driver driver = this.drivers.get(env.getDriver());
        if (driver == null) {
            throw new IllegalArgumentException("can't find driver " + env);
        }
        return driver;
    }

    public Set<String> getDriverList() {
        return drivers.keySet();
    }
}
