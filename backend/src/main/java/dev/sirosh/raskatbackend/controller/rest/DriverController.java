package dev.sirosh.raskatbackend.controller.rest;

import dev.sirosh.raskatbackend.driver.DriverHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/rest/driver")
@RequiredArgsConstructor
public class DriverController {
    private final DriverHolder driverHolder;

    @GetMapping
    public Set<String> get() {
        return driverHolder.getDriverList();
    }
}
