package dev.sirosh.raskatbackend.driver.kubernetes.mapper;

import dev.sirosh.raskatbackend.entity.App;
import dev.sirosh.raskatbackend.entity.Container;
import dev.sirosh.raskatbackend.entity.EnvironmentConfig;
import dev.sirosh.raskatbackend.entity.Port;
import io.kubernetes.client.custom.IntOrString;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.models.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Mapping(source = "name", target = "metadata.name")
    @Mapping(source = "app", target = "spec.selector", qualifiedByName = "selector")
    @Mapping(source = "app", target = "spec.template.metadata.labels", qualifiedByName = "labels")
    @Mapping(source = "container", target = "spec.template.spec.containers")
    V1Deployment toDeployment(App app);

    @Named("selector")
    default V1LabelSelector selector(App app) {
        V1LabelSelector selector = new V1LabelSelector();
        selector.putMatchLabelsItem("app", app.getName());
        return selector;
    }

    @Named("labels")
    default Map<String, String> labels(App app) {
        Map<String, String> labels = new HashMap<>();
        labels.put("app", app.getName());
        return labels;
    }

    @Mapping(source = "image", target = "image")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "ports", target = "ports")
    V1Container toContainer(Container container);

    @Mapping(source = "destination", target = "containerPort")
    V1ContainerPort toContainerPort(Port port);

    @Mapping(source = "host", target = "basePath")
    ApiClient toClient(EnvironmentConfig environment);

    @Mapping(source = "name", target = "metadata.name")
    @Mapping(source = "app", target = "spec.ports", qualifiedByName = "ports")
    @Mapping(source = "app", target = "spec.selector", qualifiedByName = "selector")
    V1Service toService(App app);

    @Named("selector")
    default Map<String, String> serviceSelector(App app) {
        return Collections.singletonMap("app.kubernetes.io/name", app.getName());
    }

    @Named("ports")
    default List<V1ServicePort> ports(App app) {
        return app.getContainer().stream()
                .flatMap(c -> c.getPorts().stream())
                .map(p -> {
                    V1ServicePort sp = new V1ServicePort();
                    sp.setPort(p.getSource());
                    sp.setTargetPort(new IntOrString(p.getDestination()));
                    return sp;
                }).collect(Collectors.toList());
    }
}
