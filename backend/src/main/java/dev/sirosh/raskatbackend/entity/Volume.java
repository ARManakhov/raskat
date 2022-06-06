package dev.sirosh.raskatbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Volume {
    private String destination;
    private String name;
    private String driver;
    private Map<String,String> label;
}
