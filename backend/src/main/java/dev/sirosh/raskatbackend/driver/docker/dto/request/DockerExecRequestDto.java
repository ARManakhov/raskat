package dev.sirosh.raskatbackend.driver.docker.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class DockerExecRequestDto {
    private boolean detach;
    private boolean tty;
}
