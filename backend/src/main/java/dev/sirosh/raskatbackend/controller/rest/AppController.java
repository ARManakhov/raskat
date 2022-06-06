package dev.sirosh.raskatbackend.controller.rest;

import dev.sirosh.raskatbackend.dto.AppDto;
import dev.sirosh.raskatbackend.dto.Result;
import dev.sirosh.raskatbackend.service.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/rest/environment/{environment}/app")
@RequiredArgsConstructor
public class AppController {
    private final AppService appService;

    @PostMapping
    public Result<AppDto> create(@PathVariable String environment, @RequestBody AppDto app) throws IOException {
        return appService.create(environment, app);
    }

    @GetMapping("{name}")
    public Result<AppDto> get(@PathVariable String environment, @PathVariable String name) {
        return appService.getDto(environment, name);
    }

    @GetMapping
    public Result<List<AppDto>> getList(@PathVariable String environment) {
        return appService.getAll(environment);
    }

    @PostMapping("{name}/start")
    public Result<Void> start(@PathVariable String environment, @PathVariable String name) throws IOException {
        return appService.start(environment, name);
    }

    @PostMapping("{name}/stop")
    public Result<Void> stop(@PathVariable String environment, @PathVariable String name) throws IOException {
        return appService.stop(environment, name);
    }

    @DeleteMapping("{name}")
    public Result<Void> delete(@PathVariable String environment, @PathVariable String name) throws IOException {
        return appService.delete(environment, name);
    }
}
