package dev.sirosh.raskatbackend.driver.docker.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class DockerCreateExecRequestDto {
    private boolean attachStdin;
    private boolean attachStdout;
    private boolean attachStderr;
    private String detachKeys;
    private String tty;
    private List<String> cmd;
    private List<String> env;
    private boolean privileged;
    private String user;
    private String workingDir;
}
