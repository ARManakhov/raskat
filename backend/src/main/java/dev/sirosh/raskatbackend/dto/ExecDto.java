package dev.sirosh.raskatbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExecDto {
    boolean attachStdOut;
    boolean attachStdErr;
    boolean tty;
    private String command;
    private Map<String, String> env;
}
