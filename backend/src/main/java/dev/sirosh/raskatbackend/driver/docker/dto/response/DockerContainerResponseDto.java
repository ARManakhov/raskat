package dev.sirosh.raskatbackend.driver.docker.dto.response;

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
public class DockerContainerResponseDto {
    private String id;
    private List<String> names;
    private String image;
    private String imageId;
    private String command;
    private int created;
    private int sizeRw;
    private int sizeRootFs;
    private Map<String, String> labels;
    private String state;
    private String status;
    private HostConfig hostConfig;

    @Data
    @Builder
    @RequiredArgsConstructor
    @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
    public static class HostConfig {
        private String networkMode;
    }

    @Data
    @Builder
    @RequiredArgsConstructor
    @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
    public static class Port {
        private String ip;
        private String privatePort;
        private String publicPort;
        private String type;
    }
}
