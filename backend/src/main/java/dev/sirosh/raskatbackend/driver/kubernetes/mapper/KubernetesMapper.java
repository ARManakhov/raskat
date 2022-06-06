package dev.sirosh.raskatbackend.driver.kubernetes.mapper;

import dev.sirosh.raskatbackend.entity.App;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface KubernetesMapper {
    default List<App> toDto(V1PodList appDtoList) {
        return toDto(appDtoList.getItems());
    }

    List<App> toDto(List<V1Pod> appDtoList);

//    @Mapping(source = "spec.containers.image", target = "container.image")
//    @Mapping(source = "spec.container.env", target = "environment")
//    @Mapping(source = "spec.container.ports", target = "ports")
//    @Mapping(source = "container", target = "healthCheck")
//    @Mapping(source = "spec.volumes", target = "volumes")
    App toDto(V1Pod appDtoList);
}
