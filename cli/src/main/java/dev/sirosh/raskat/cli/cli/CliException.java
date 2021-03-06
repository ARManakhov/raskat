package dev.sirosh.raskat.cli.cli;

@SuppressWarnings("serial")
public class CliException extends Exception {

    protected int exitCode;

    protected static final int DEFAULT_EXCEPTION_EXIT_CODE = 1;

    public CliException(String message) {
        this(message, DEFAULT_EXCEPTION_EXIT_CODE);
    }

    public CliException(String message, int exitCode) {
        super(message);
        this.exitCode = exitCode;
    }

    public CliException(Throwable cause) {
        this(cause, DEFAULT_EXCEPTION_EXIT_CODE);
    }

    public CliException(Throwable cause, int exitCode) {
        super(cause);
        this.exitCode = exitCode;
    }

    public CliException(String message, Throwable cause) {
        this(message, cause, DEFAULT_EXCEPTION_EXIT_CODE);
    }

    public CliException(String message, Throwable cause, int exitCode) {
        super(message, cause);
        this.exitCode = exitCode;
    }

    public int getExitCode() {
        return exitCode;
    }

}
