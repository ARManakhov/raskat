package dev.sirosh.raskatbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContainerDto {
    private String image;
    private String containerId;
//    private Map<String, String> environment;
    private List<ExecDto> exec;
    private List<PortDto> ports;
    private List<VolumeDto> volumes;
}

