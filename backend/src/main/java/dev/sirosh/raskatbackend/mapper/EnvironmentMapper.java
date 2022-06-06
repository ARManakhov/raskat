package dev.sirosh.raskatbackend.mapper;

import dev.sirosh.raskatbackend.dto.EnvironmentDto;
import dev.sirosh.raskatbackend.entity.Environment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AppMapper.class})
public interface EnvironmentMapper {

    EnvironmentDto toDto(Environment entity);

    List<EnvironmentDto> toDto(List<Environment> entity);

    Environment toEntity(EnvironmentDto dto);

    List<Environment> toEntity(List<EnvironmentDto> dto);
}
