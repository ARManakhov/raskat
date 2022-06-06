package dev.sirosh.raskat.cli.dto;

import dev.sirosh.raskatbackend.entity.VolumeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VolumeDto {
    private String destination;
    private String source;
    private VolumeType type;
}
