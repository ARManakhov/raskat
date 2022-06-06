package dev.sirosh.raskatbackend.driver.docker.mapper;

import dev.sirosh.raskatbackend.driver.docker.dto.request.DockerCreateContainerRequestDto;
import dev.sirosh.raskatbackend.driver.docker.dto.response.DockerContainerResponseDto;
import dev.sirosh.raskatbackend.entity.App;
import dev.sirosh.raskatbackend.entity.Port;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class DockerRequestMapper {
    // TODO: 29.05.2022 remap due to new changes of app structure
//    @Mapping(source = "container.image", target = "image")
    @Mapping(source = "ports", target = "exposedPorts", qualifiedByName = "portQualifier")
    public abstract DockerCreateContainerRequestDto toDockerCreateConfig(App config);

    public abstract App toCommonConfig(DockerCreateContainerRequestDto config);

    public abstract App containerToCommonConfig(DockerContainerResponseDto config);

    public abstract List<App> containerToCommonConfig(List<DockerContainerResponseDto> config);

    @Named("portQualifier")
    protected Map<String, String> portQualifier(List<Port> port) {
        return port.stream().collect(Collectors.toMap(p -> p.getSource() + "/" + p.getType(), p -> p.getDestination().toString()));
    }
}
