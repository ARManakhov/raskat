package dev.sirosh.raskatbackend.dto;

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
    private List<ContainerDto> container;
    private Map<String, String> labels;
    private VolumeDto volumeDto;
}
