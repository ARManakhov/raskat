package dev.sirosh.raskatbackend.service;

import dev.sirosh.raskatbackend.dto.AppDto;
import dev.sirosh.raskatbackend.dto.Result;

import java.io.IOException;
import java.util.List;

public interface AppService {
    Result<List<AppDto>> create(String environment, List<AppDto> appDto) throws IOException;

    Result<Void> delete(String environment, String name) throws IOException;

    Result<AppDto> getDto(String environment, String name);

    // TODO: 19.05.2022 add filters
    Result<List<AppDto>> getAll(String environment);

    Result<Void> start(String environment, String name) throws IOException;

    Result<Void> stop(String environment, String name) throws IOException;
}
