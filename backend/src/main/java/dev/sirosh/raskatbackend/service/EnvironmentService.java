package dev.sirosh.raskatbackend.service;

import dev.sirosh.raskatbackend.dto.EnvironmentDto;
import dev.sirosh.raskatbackend.entity.Environment;

import java.io.IOException;
import java.util.List;

public interface EnvironmentService {
    // TODO: 19.05.2022 add filters
    List<EnvironmentDto> getAll();

    // TODO: 19.05.2022 add check availability before return results
    EnvironmentDto getDto(String name);

    Environment getEntity(String name);

    // TODO: 19.05.2022 on create get all existing containers/services/pods or else
    void saveOrUpdate(EnvironmentDto environmentDto) throws IOException;

    boolean delete(String environment) throws IOException;

    boolean ping(String environment) throws IOException;

    EnvironmentDto scrape(String name) throws IOException;
}
