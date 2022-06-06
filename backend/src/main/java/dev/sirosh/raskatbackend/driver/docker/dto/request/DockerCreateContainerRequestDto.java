package dev.sirosh.raskatbackend.driver.docker.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class DockerCreateContainerRequestDto {
    private String hostName;
    private String domainName;
    private String user;
    private Boolean attachStdin;
    private Boolean attachStdout;
    private Boolean attachStderr;
    private Boolean tty;
    private Boolean openStdin;
    private Boolean stdinOnce;
    private List<String> env;
    private List<String> cmd;
    private String entrypoint;
    private String image;
    private Map<String, String> label;
    private String workingDir;
    private Boolean networkDisabled;
    private String macAddress;
    private Map<String, String> exposedPorts;
    private String stopSignal;
    private Integer stopTimeout;
}
