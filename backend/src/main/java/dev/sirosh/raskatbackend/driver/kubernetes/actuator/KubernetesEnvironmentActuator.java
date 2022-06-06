package dev.sirosh.raskatbackend.driver.kubernetes.actuator;

import dev.sirosh.raskatbackend.driver.base.actuator.EnvironmentActuator;
import dev.sirosh.raskatbackend.driver.kubernetes.mapper.KubernetesMapper;
import dev.sirosh.raskatbackend.entity.App;
import dev.sirosh.raskatbackend.entity.EnvironmentConfig;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.util.Config;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class KubernetesEnvironmentActuator implements EnvironmentActuator {
    private final KubernetesMapper kubernetesMapper;

    @Override
    public boolean ping(EnvironmentConfig environment) throws IOException {
        throw new UnsupportedOperationException("not implemented yet"); //not supported by kubernetes cluster
    }

    @Override
    @SneakyThrows // TODO: 29.05.2022 catch exception properly
    public List<App> scrapeContainers(EnvironmentConfig environment) throws IOException {
        ApiClient client = Config.defaultClient();
        Configuration.setDefaultApiClient(client);

        CoreV1Api api = new CoreV1Api();
        V1PodList list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null, false);
        return kubernetesMapper.toDto(list);
    }
}
