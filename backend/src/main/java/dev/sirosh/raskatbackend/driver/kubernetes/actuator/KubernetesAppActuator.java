package dev.sirosh.raskatbackend.driver.kubernetes.actuator;

import dev.sirosh.raskatbackend.driver.base.actuator.AppActuator;
import dev.sirosh.raskatbackend.driver.kubernetes.mapper.KubernetesMapper;
import dev.sirosh.raskatbackend.entity.App;
import dev.sirosh.raskatbackend.entity.EnvironmentConfig;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Deployment;
import io.kubernetes.client.openapi.models.V1DeploymentList;
import io.kubernetes.client.openapi.models.V1DeploymentSpec;
import io.kubernetes.client.openapi.models.V1Service;
import io.kubernetes.client.util.Config;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class KubernetesAppActuator implements AppActuator {
    private final KubernetesMapper kubernetesMapper;

    /**
     * method to create app in environment or update it if it already exists
     *
     * @param app
     * @return
     * @throws IOException
     */
    @Override
    public App modify(App app, EnvironmentConfig environment) throws IOException {
        createPod(app);
        createService(app);
        return null;
    }

    private void createService(App app) throws IOException {
        ApiClient client = Config.defaultClient();
        Configuration.setDefaultApiClient(client);
        V1Service service = kubernetesMapper.toService(app);
        CoreV1Api api = new CoreV1Api();

        try {
            api.createNamespacedService("default", service, null, null, null, null);
        } catch (ApiException e) {
            log.error("Error creating service", e);
        }
    }

    private App createPod(App app) throws IOException {
        ApiClient client = Config.defaultClient();
        Configuration.setDefaultApiClient(client);
        AppsV1Api api = new AppsV1Api();
        Optional<V1Deployment> deployment = getDeployment(app.getName(), api);
        V1Deployment appDeploySpec = kubernetesMapper.toDeployment(app);
        try {
            if (deployment.isPresent()) {
                api.replaceNamespacedDeployment(app.getName(), "default", appDeploySpec, null, null, null, null);
            } else {
                api.createNamespacedDeployment("default", appDeploySpec, null, null, null, null);
            }
        } catch (ApiException e) {
            log.error("Error while creating app", e);
        }
        return null;
    }


    @Override
    public boolean stop(App app, EnvironmentConfig environment) throws IOException {
        return scale(app.getName(), 0);
    }


    @Override
    public boolean start(App app, EnvironmentConfig environment) throws IOException {
        return scale(app.getName(), 1);
    }

    private boolean scale(String name, int num) {
        AppsV1Api appsV1Api = new AppsV1Api();
        getDeployment(name, appsV1Api)
                .ifPresent((V1Deployment deploy) -> {
                    try {
                        V1DeploymentSpec newSpec = deploy.getSpec().replicas(num);
                        V1Deployment newDeploy = deploy.spec(newSpec);
                        appsV1Api.replaceNamespacedDeployment(
                                name, "default", newDeploy, null, null, null, null);
                    } catch (ApiException ex) {
                        log.warn("Scale the pod failed for Deployment:" + name, ex);
                    }
                });
        return true;
    }

    @Override
    public boolean delete(App app, EnvironmentConfig environment) throws IOException {
        String name = app.getName();
        AppsV1Api appsV1Api = new AppsV1Api();
        Optional<V1Deployment> foundDeployment = getDeployment(name, appsV1Api);
        foundDeployment.ifPresent((V1Deployment deploy) -> {
            try {
                appsV1Api.deleteNamespacedDeployment(name, "default", null, null, null, null, null, null);
            } catch (ApiException ex) {
                log.warn("delete failed for Deployment:" + name, ex);
            }
        });
        return true;
    }

    private Optional<V1Deployment> getDeployment(String name, AppsV1Api appsV1Api) {
        try {
            V1DeploymentList listNamespacedDeployment;

            listNamespacedDeployment = appsV1Api.listNamespacedDeployment("default", null, null, null, null, null, null, null, null, null, Boolean.FALSE);
            return listNamespacedDeployment.getItems().stream()
                    .filter((V1Deployment deployment) -> deployment.getMetadata().getName().equals(name))
                    .findFirst();
        } catch (ApiException e) {
            throw new IllegalArgumentException("can,t connect to api");
        }
    }
}
