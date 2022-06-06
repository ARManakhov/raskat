package dev.sirosh.raskat.cli.cli;

import java.util.HashMap;

public class RootCommand extends CliResourceCommand {
    private static final HashMap<String, CliCommand> cliCommands = new HashMap<>();

    static {
        new AppCommand().addToMap(cliCommands);
        new EnvCommand().addToMap(cliCommands);
    }

    public RootCommand() {
        super(null, cliCommands);
    }
}
