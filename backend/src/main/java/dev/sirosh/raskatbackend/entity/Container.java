package dev.sirosh.raskatbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Container {
    private String image;
    private String pullPolicy;
    private String name;
    private Map<String, String> env;
    private List<Exec> exec;
    private List<Port> ports;
    private List<Volume> volumes;
}
