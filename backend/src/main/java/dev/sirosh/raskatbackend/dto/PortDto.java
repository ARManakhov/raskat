package dev.sirosh.raskatbackend.dto;


import dev.sirosh.raskatbackend.entity.PortType;
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
