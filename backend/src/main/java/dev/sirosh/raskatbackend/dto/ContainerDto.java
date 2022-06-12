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
    private String name;
    private List<ExecDto> exec;
    private List<PortDto> ports;
    private List<VolumeDto> volumes;
}

