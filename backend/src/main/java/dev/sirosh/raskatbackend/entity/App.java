package dev.sirosh.raskatbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class App {
    @Id
    private String name;
    private boolean enabled;
    // TODO: 29.05.2022 app can have more than one container, make it list
    private List<Container> container;
    private List<Port> ports;
    private Map<String, String> labels;
    private List<Volume> volumes;
    private HealthCheck healthCheck;
}
