package dev.sirosh.raskatbackend.controller.rest;

import dev.sirosh.raskatbackend.dto.EnvironmentDto;
import dev.sirosh.raskatbackend.service.EnvironmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/rest/environment")
@RequiredArgsConstructor
public class EnvironmentController {
    private final EnvironmentService environmentService;

    @PostMapping
    public void add(@RequestBody EnvironmentDto environment) throws IOException {
        environmentService.saveOrUpdate(environment);
    }

    @GetMapping("{name}")
    public EnvironmentDto get(@PathVariable String name) {
        return environmentService.getDto(name);
    }


    @DeleteMapping("{name}")
    public boolean delete(@PathVariable String name) throws IOException {
        return environmentService.delete(name);
    }


    @GetMapping("{name}/ping")
    public boolean ping(@PathVariable String name) throws IOException {
        return environmentService.ping(name);
    }

    @GetMapping
    public List<EnvironmentDto> getList() {
        return environmentService.getAll();
    }


    @GetMapping("{name}/scrape")
    public EnvironmentDto scrape(@PathVariable String name) throws IOException {
        return environmentService.scrape(name);
    }
}
