package dev.sirosh.raskatbackend.mapper;

import dev.sirosh.raskatbackend.dto.AppDto;
import dev.sirosh.raskatbackend.entity.App;
import dev.sirosh.raskatbackend.entity.Environment;
import dev.sirosh.raskatbackend.repository.EnvironmentRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class AppMapper {
    @Autowired
    EnvironmentRepository environmentRepository;

//    @Mapping(source = "environment.name", target = "environment")
    public abstract AppDto toDto(App entity);

    public abstract List<AppDto> toDto(List<App> entity);

//    @Mapping(source = "environment", target = "environment", qualifiedByName = "envGetter")
    public abstract App toEntity(AppDto dto);

    public abstract List<App> toEntity(List<AppDto> dto);

    @Named("envGetter")
    protected Environment getEnv(String env) {
        return environmentRepository.findById(env)
                .orElseThrow(() -> new IllegalArgumentException("can't find environment " + env));
    }
}
