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
    private String description;
    private boolean enabled;
    private List<Container> container;
    private Map<String, String> labels;
    private HealthCheck healthCheck;
    private List<Volume> volumes;
}
