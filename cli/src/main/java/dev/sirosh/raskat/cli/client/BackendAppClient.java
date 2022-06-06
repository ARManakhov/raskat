package dev.sirosh.raskat.cli.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import dev.sirosh.raskat.cli.dto.AppDto;
import dev.sirosh.raskat.cli.dto.Result;
import okhttp3.*;

import java.io.IOException;
import java.util.List;

public class BackendAppClient {
    private static ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.setDateFormat(new StdDateFormat());
        return mapper;
    }

    private <T> T makeRequest(Request request) throws RequestException {
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            if (response.body() != null) {
                if (response.isSuccessful()) {
                    return objectMapper().readValue(response.body().byteStream(), new TypeReference<>() {
                    });
                } else {
                    throw new RequestException("response have " + response.code() + " code",
                            objectMapper().readValue(response.body().byteStream(), new TypeReference<>() {
                            }));
                }
            }
            throw new RequestException("response body is null", null);
        } catch (IOException e) {
            throw new RequestException(e.getMessage(), null);
        }
    }

    public Result<List<AppDto>> getApps(String server) throws RequestException {
        Request request = new Request.Builder()
                .url(new HttpUrl.Builder()
                        .host(server)
                        .addPathSegments("rest/app")
                        .build())
                .method("GET", null)
                .build();
        return makeRequest(request);
    }

    public Result<AppDto> getApp(String server, String name) throws RequestException {
        Request request = new Request.Builder()
                .url(new HttpUrl.Builder()
                        .host(server)
                        .addPathSegments("rest/app")
                        .addPathSegment(name)
                        .build())
                .method("GET", null)
                .build();
        return makeRequest(request);
    }

    public Result<Void> startApp(String server, String name) throws RequestException {
        Request request = new Request.Builder()
                .url(new HttpUrl.Builder()
                        .host(server)
                        .addPathSegments("rest/app")
                        .addPathSegment(name)
                        .addPathSegment("start")
                        .build())
                .method("POST", null)
                .build();
        return makeRequest(request);
    }

    public Result<Void> stopApp(String server, String name) throws RequestException {
        Request request = new Request.Builder()
                .url(new HttpUrl.Builder()
                        .host(server)
                        .addPathSegments("rest/app")
                        .addPathSegment(name)
                        .addPathSegment("stop")
                        .build())
                .method("POST", null)
                .build();
        return makeRequest(request);
    }


    public Result<Void> deleteApp(String server, String name) throws RequestException {
        Request request = new Request.Builder()
                .url(new HttpUrl.Builder()
                        .host(server)
                        .addPathSegments("rest/app")
                        .addPathSegment(name)
                        .build())
                .method("DELETE", null)
                .build();
        return makeRequest(request);
    }

    public Result<Void> createApp(String server, AppDto app) throws RequestException {
        Request request = null;
        try {
            request = new Request.Builder()
                    .url(new HttpUrl.Builder()
                            .host(server)
                            .addPathSegments("rest/app")
                            .build())
                    .method("POST", RequestBody.create(objectMapper().writeValueAsBytes(app), MediaType.parse("application/json")))
                    .build();
        } catch (JsonProcessingException e) {
            throw new RequestException(e.getMessage(), null);
        }
        return makeRequest(request);
    }
}
