package dev.sirosh.raskat.cli.cli;


import java.util.Map;

/**
 * base class for all CLI commands
 */
public abstract class CliCommand {
    protected final String cmdName;

    public CliCommand(String cmdName) {
        this.cmdName = cmdName;
    }

    /**
     * add this command to a map. Use the command string as key.
     *
     * @param cmdMap
     */
    public void addToMap(Map<String, CliCommand> cmdMap) {
        cmdMap.put(cmdName, this);
    }

    /**
     * parse the command arguments
     *
     * @param cmdArgs
     * @return this CliCommand
     * @throws CliParseException
     */
    public abstract CliCommand parse(String[] cmdArgs) throws CliException;

    /**
     * @param server
     * @throws CliException
     */
    public abstract void exec(String server) throws CliException;
}
