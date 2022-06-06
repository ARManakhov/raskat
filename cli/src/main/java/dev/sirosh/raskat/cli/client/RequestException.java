package dev.sirosh.raskat.cli.client;

import dev.sirosh.raskat.cli.dto.Result;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RequestException extends Exception{
    private final String message;
//    private boolean sucess;
    private final Result<Void> result;
}

