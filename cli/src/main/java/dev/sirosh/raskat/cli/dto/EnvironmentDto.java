package dev.sirosh.raskat.cli.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnvironmentDto {
    private String driver;
    private String name;
    private EnvironmentConfigDto config;
    private List<AppDto> apps;
}
