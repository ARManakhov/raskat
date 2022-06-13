package dev.sirosh.raskatbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContainerDto {
    private String image;
    private String name;
    private String pullPolicy;
    private Map<String, String> env;
    private List<ExecDto> exec;
    private List<PortDto> ports;
    private List<VolumeDto> volumes;
}

