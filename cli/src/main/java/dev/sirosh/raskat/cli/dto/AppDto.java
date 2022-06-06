package dev.sirosh.raskat.cli.dto;

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
    private String environment;
    private String driver;
    private String name;
    private List<ContainerDto> container;
    private List<PortDto> ports;
    private Map<String, String> labels;
    private List<VolumeDto> volumes;
}
