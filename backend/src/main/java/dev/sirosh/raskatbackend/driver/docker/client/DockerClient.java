package dev.sirosh.raskatbackend.driver.docker.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import dev.sirosh.raskatbackend.driver.docker.dto.request.DockerCreateContainerRequestDto;
import dev.sirosh.raskatbackend.driver.docker.dto.response.DockerContainerResponseDto;
import dev.sirosh.raskatbackend.driver.docker.dto.response.DockerCreateResponseDto;
import dev.sirosh.raskatbackend.entity.EnvironmentConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DockerClient {
    private final ObjectMapper objectMapper;

    protected DockerHttpClient connect(EnvironmentConfig environment) throws IOException {
        DockerClientConfig config = createConfig(environment);
        return new ApacheDockerHttpClient.Builder()
                .dockerHost(config.getDockerHost())
                .sslConfig(config.getSSLConfig())
                .maxConnections(100)
                .connectionTimeout(Duration.ofSeconds(30))
                .responseTimeout(Duration.ofSeconds(45))
                .build();
    }

    protected void disconnect(DockerHttpClient connection) throws IOException {
        connection.close();
    }

    private DockerClientConfig createConfig(EnvironmentConfig environment) {
        return DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost(environment.getHost())
//                .withDockerTlsVerify(true)
//                .withDockerCertPath("/home/user/.docker")
//                .withRegistryUsername(registryUser)
//                .withRegistryPassword(registryPass)
//                .withRegistryEmail(registryMail)
//                .withRegistryUrl(registryUrl)
                .build();
    }


    public boolean ping(EnvironmentConfig environmentConfig) throws IOException {
        DockerHttpClient.Request request = DockerHttpClient.Request.builder()
                .method(DockerHttpClient.Request.Method.GET)
                .path("/_ping")
                .build();
        DockerHttpClient connection = connect(environmentConfig);
        try (DockerHttpClient.Response response = connection.execute(request)) {
            return response.getStatusCode() == 200;
        } catch (Exception e) {
            return false;
        } finally {
            disconnect(connection);
        }
    }


    public DockerCreateResponseDto containerCreate(DockerCreateContainerRequestDto appDto, EnvironmentConfig environment) throws IOException {
        DockerHttpClient.Request request = DockerHttpClient.Request.builder()
                .method(DockerHttpClient.Request.Method.POST)
                .putHeader("Content-type", "application/json")
                .path("/containers/create")
                .bodyBytes(objectMapper.writeValueAsBytes(appDto))
                .build();
        DockerHttpClient connection = connect(environment);
        DockerHttpClient.Response response = connection.execute(request);
        disconnect(connection);
        if (response.getStatusCode() == 201) {
            return objectMapper.readValue(response.getBody(), DockerCreateResponseDto.class);
        } else {
            throw new IllegalArgumentException("can't create");
        }
    }


    public boolean containerStop(String containerId, EnvironmentConfig environment) throws IOException {
        DockerHttpClient.Request request = DockerHttpClient.Request.builder()
                .method(DockerHttpClient.Request.Method.POST)
                .path("/containers/" + containerId + "/stop")
                .build();
        DockerHttpClient connection = connect(environment);
        try (DockerHttpClient.Response response = connection.execute(request)) {
            return response.getStatusCode() == 200;
        } catch (Exception e) {
            return false;
        } finally {
            disconnect(connection);
        }
    }


    public boolean containerStart(String containerId, EnvironmentConfig environment) throws IOException {
        DockerHttpClient.Request request = DockerHttpClient.Request.builder()
                .method(DockerHttpClient.Request.Method.POST)
                .path("/containers/" + containerId + "/start")
                .build();
        DockerHttpClient connection = connect(environment);
        try (DockerHttpClient.Response response = connection.execute(request)) {
            return response.getStatusCode() == 200;
        } catch (Exception e) {
            return false;
        } finally {
            disconnect(connection);
        }
    }


    public boolean containerDelete(String containerId, EnvironmentConfig environment) throws IOException {
        DockerHttpClient.Request request = DockerHttpClient.Request.builder()
                .method(DockerHttpClient.Request.Method.DELETE)
                .path("/containers/" + containerId)
                .build();
        DockerHttpClient connection = connect(environment);
        try (DockerHttpClient.Response response = connection.execute(request)) {
            return response.getStatusCode() == 200;
        } catch (Exception e) {
            return false;
        } finally {
            disconnect(connection);
        }
    }

    public DockerCreateResponseDto containerExecCreate(String containerId, EnvironmentConfig environment) throws IOException {
        DockerHttpClient.Request request = DockerHttpClient.Request.builder()
                .method(DockerHttpClient.Request.Method.POST)
                .path("/containers/" + containerId + "/exec")
                .build();
        DockerHttpClient connection = connect(environment);
        DockerHttpClient.Response response = connection.execute(request);
        disconnect(connection);
        if (response.getStatusCode() == 201) {
            return objectMapper.readValue(response.getBody(), DockerCreateResponseDto.class);
        } else {
            throw new IllegalArgumentException("can't create");
        }
    }


    public boolean containerExecStart(String execId, EnvironmentConfig environment) throws IOException {
        DockerHttpClient.Request request = DockerHttpClient.Request.builder()
                .method(DockerHttpClient.Request.Method.POST)
                .path("/exec/" + execId + "/start")
                .build();
        DockerHttpClient connection = connect(environment);
        try (DockerHttpClient.Response response = connection.execute(request)) {
            return response.getStatusCode() == 200;
        } catch (Exception e) {
            return false;
        } finally {
            disconnect(connection);
        }
    }

    public List<DockerContainerResponseDto> containersList(EnvironmentConfig environment) throws IOException {
        DockerHttpClient.Request request = DockerHttpClient.Request.builder()
                .method(DockerHttpClient.Request.Method.GET)
                .path("/containers/json")
                .build();
        DockerHttpClient connection = connect(environment);
        DockerHttpClient.Response response = connection.execute(request);
        disconnect(connection);
        if (response.getStatusCode() == 200) {
            return objectMapper.readValue(response.getBody(), new TypeReference<>() {
            });
        } else {
            throw new IllegalArgumentException("can't create");
        }
    }
}
