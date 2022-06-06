package dev.sirosh.raskatbackend.driver.kubernetes;

import dev.sirosh.raskatbackend.driver.base.Driver;
import dev.sirosh.raskatbackend.driver.kubernetes.actuator.KubernetesAppActuator;
import dev.sirosh.raskatbackend.driver.kubernetes.actuator.KubernetesEnvironmentActuator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor
public class KubernetesDriver implements Driver {
    private final KubernetesAppActuator appActuator;
    private final KubernetesEnvironmentActuator environmentActuator;
}
