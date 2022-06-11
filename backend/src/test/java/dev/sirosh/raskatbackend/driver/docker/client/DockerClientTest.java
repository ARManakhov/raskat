package dev.sirosh.raskatbackend.driver.docker.client;

import dev.sirosh.raskatbackend.driver.docker.dto.request.DockerCreateContainerRequestDto;
import dev.sirosh.raskatbackend.driver.docker.dto.response.DockerContainerResponseDto;
import dev.sirosh.raskatbackend.driver.docker.dto.response.DockerCreateResponseDto;
import dev.sirosh.raskatbackend.entity.EnvironmentConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static dev.sirosh.raskatbackend.Utils.objectMapper;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DockerClientTest {
    DockerClient dockerClient = new DockerClient(objectMapper());
    private static final EnvironmentConfig standardEnv = EnvironmentConfig.builder()
            .host("unix:///var/run/docker.sock")
            .build();

    @Test
    void ping() throws IOException {
        boolean ping = dockerClient.ping(standardEnv);
        assertTrue(ping);
    }

    @Test
    void containerCreate() throws IOException {
        DockerCreateResponseDto dockerCreateResponseDto =
                dockerClient.containerCreate(DockerCreateContainerRequestDto.builder()
                                .image("hello-world")
                                .build(),
                        standardEnv);
        assertNotNull(dockerCreateResponseDto.getId());
    }

    @Test
    void containerStart() throws IOException {
        DockerCreateResponseDto dockerCreateResponseDto =
                dockerClient.containerCreate(DockerCreateContainerRequestDto.builder()
                                .image("hello-world")
                                .build(),
                        standardEnv);
        boolean res = dockerClient.containerStart(dockerCreateResponseDto.getId(),
                standardEnv);
        assertTrue(res);
    }

    @Test
    void containerDelete() throws IOException {
        DockerCreateResponseDto dockerCreateResponseDto =
                dockerClient.containerCreate(DockerCreateContainerRequestDto.builder()
                                .image("hello-world")
                                .build(),
                        standardEnv);
        boolean res = dockerClient.containerDelete(dockerCreateResponseDto.getId(),
                standardEnv);
        assertTrue(res);
        List<DockerContainerResponseDto> containers = dockerClient.containersList(standardEnv);
        assertFalse(containers.stream().anyMatch(c -> dockerCreateResponseDto.getId().equals(c.getId())));
    }
}