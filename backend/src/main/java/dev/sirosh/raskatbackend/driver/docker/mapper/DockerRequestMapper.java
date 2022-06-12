package dev.sirosh.raskatbackend.driver.docker.mapper;

import dev.sirosh.raskatbackend.driver.docker.dto.request.DockerCreateContainerRequestDto;
import dev.sirosh.raskatbackend.driver.docker.dto.response.DockerContainerResponseDto;
import dev.sirosh.raskatbackend.entity.App;
import dev.sirosh.raskatbackend.entity.Container;
import dev.sirosh.raskatbackend.entity.Port;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;

@Mapper(componentModel = "spring")
public abstract class DockerRequestMapper {

    public abstract App toCommonConfig(DockerCreateContainerRequestDto config);

    public abstract App containerToCommonConfig(DockerContainerResponseDto config);

    public abstract List<App> containerToCommonConfig(List<DockerContainerResponseDto> config);

    @Named("port")
    protected Map<String, Object> portExpose(List<Port> ports) {
        if (ports == null) {
            return null;
        } else {
            HashMap<String, Object> exposedPort = new HashMap<>();
            for (Port p : ports) {
                exposedPort.put(p.getSource() + "/" + p.getType(), new Object());
            }
            return exposedPort;
        }
    }

    @Named("port")
    protected Map<String, List<DockerCreateContainerRequestDto.PortBinding>> portBind(List<Port> ports) {
        if (ports == null) {
            return null;
        } else {
            Map<String, List<DockerCreateContainerRequestDto.PortBinding>> portBindings = new HashMap<>();
            for (Port p : ports) {
                portBindings.put(p.getSource() + "/" + p.getType(), singletonList(new DockerCreateContainerRequestDto.PortBinding(p.getDestination().toString())));
            }
            return portBindings;
        }
    }


    @Mapping(source = "image", target = "image")
    @Mapping(source = "ports", target = "exposedPorts", qualifiedByName = "port")
    @Mapping(source = "ports", target = "hostConfig.portBindings", qualifiedByName = "port")
    public abstract DockerCreateContainerRequestDto toCreateRequestDto(Container contianer);

}
