package dev.sirosh.raskatbackend.driver.kubernetes.actuator;

import dev.sirosh.raskatbackend.driver.base.actuator.AppActuator;
import dev.sirosh.raskatbackend.driver.kubernetes.mapper.KubernetesMapper;
import dev.sirosh.raskatbackend.entity.App;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class KubernetesAppActuator implements AppActuator {
    private final KubernetesMapper kubernetesMapper;

    @Override
    public List<App> modify(App app) throws IOException {
        return null;
    }

    @Override
    public boolean stop(App app) throws IOException {
        return false;
    }

    @Override
    public boolean start(App app) throws IOException {
        return false;
    }

    @Override
    public boolean delete(App app) throws IOException {
        return false;
    }

    @Override
    public List<App> modify(App app) throws IOException {
        ApiClient client = Config.defaultClient();
        Configuration.setDefaultApiClient(client);
        CoreV1Api api = new CoreV1Api();
        V1PodList list = api.patchNamespacedPod(null, kubernetesMapper.toKube(app), null, null, null, null);
        return kubernetesMapper.toDto(list);
    }

    @Override
    public boolean stop(App app) throws IOException {
        try {
            return scale(app.getName(), 0);
        } catch (ApiException e) {
            throw new IllegalArgumentException("can,t scale " + app.getName());
        }
    }


    @Override
    public boolean start(App app) throws IOException {
        try {
            return scale(app.getName(), 1);
        } catch (ApiException e) {
            throw new IllegalArgumentException("can,t scale " + app.getName());
        }
    }

    private boolean scale(String name, int num) throws ApiException {
        AppsV1Api appsV1Api = new AppsV1Api();
        V1DeploymentList listNamespacedDeployment =
                appsV1Api.listNamespacedDeployment(null, null, null, null, null, null, null, null, null, null, Boolean.FALSE);

        List<V1Deployment> appsV1DeploymentItems = listNamespacedDeployment.getItems();
        Optional<V1Deployment> findedDeployment =
                appsV1DeploymentItems.stream()
                        .filter(
                                (V1Deployment deployment) ->
                                        deployment.getMetadata().getName().equals(name))
                        .findFirst();
        findedDeployment.ifPresent(
                (V1Deployment deploy) -> {
                    try {
                        V1DeploymentSpec newSpec = deploy.getSpec().replicas(num);
                        V1Deployment newDeploy = deploy.spec(newSpec);
                        appsV1Api.replaceNamespacedDeployment(
                                name, null, newDeploy, null, null, null, null);
                    } catch (ApiException ex) {
                        log.warn("Scale the pod failed for Deployment:" + name, ex);
                    }
                });
        return true;
    }

    @Override
    public boolean delete(App app) throws IOException {
        String name = app.getName();
        AppsV1Api appsV1Api = new AppsV1Api();
        V1DeploymentList listNamespacedDeployment;
        try {
            listNamespacedDeployment = appsV1Api.listNamespacedDeployment(null, null, null, null, null, null, null, null, null, null, Boolean.FALSE);
        } catch (ApiException e) {
            throw new IllegalArgumentException("can,t delete " + name);
        }

        List<V1Deployment> appsV1DeploymentItems = listNamespacedDeployment.getItems();
        Optional<V1Deployment> findedDeployment =
                appsV1DeploymentItems.stream()
                        .filter(
                                (V1Deployment deployment) ->
                                        deployment.getMetadata().getName().equals(name))
                        .findFirst();
        findedDeployment.ifPresent(
                (V1Deployment deploy) -> {
                    try {
                        appsV1Api.deleteNamespacedDeployment(name, null, null, null, null, null, null, null);
                    } catch (ApiException ex) {
                        log.warn("delete failed for Deployment:" + name, ex);
                    }
                });
        throw new UnsupportedOperationException("not implemented yet");
    }
}
