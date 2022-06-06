package dev.sirosh.raskat.cli.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PortDto {
    private int source;
    private int destination;
    private PortType type;
}
