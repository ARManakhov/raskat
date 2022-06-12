package dev.sirosh.raskatbackend.dto;

import dev.sirosh.raskatbackend.entity.HealthCheck;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppDto {
    private String name;
    private String description;
    private boolean enabled;
    private List<ContainerDto> container;
    private Map<String, String> labels;
    private HealthCheck healthCheck;
    private List<VolumeDto> volumes;
}
