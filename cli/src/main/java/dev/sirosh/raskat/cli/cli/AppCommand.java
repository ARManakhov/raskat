package dev.sirosh.raskat.cli.cli;

import java.util.HashMap;

public class AppCommand extends CliResourceCommand {
    private static final HashMap<String, CliCommand> cliCommands = new HashMap<>();

    static {
        new AppCreateCommand().addToMap(cliCommands);
        new AppGetCommand().addToMap(cliCommands);
//        cliCommands.put("stop", new AppCreateCommand());
//        cliCommands.put("get", new AppCreateCommand());
//        cliCommands.put("start", new AppCreateCommand());
//        cliCommands.put("AAA", new AppCreateCommand());
    }

    public AppCommand() {
        super("app", cliCommands);
    }
}
