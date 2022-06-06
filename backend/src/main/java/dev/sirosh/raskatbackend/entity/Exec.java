package dev.sirosh.raskatbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Exec {
    boolean attachStdOut;
    boolean attachStdErr;
    boolean tty;
    private String command;
    private Map<String, String> env;
}
