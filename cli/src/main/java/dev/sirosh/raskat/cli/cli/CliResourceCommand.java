package dev.sirosh.raskat.cli.cli;


import java.util.Map;

/**
 * base class for CLI commands that points to resource
 */
public abstract class CliResourceCommand extends CliCommand {
    private final Map<String, CliCommand> commandMapCli;

    public CliResourceCommand(String cmdName, Map<String, CliCommand> commandMapCli) {
        super(cmdName);
        this.commandMapCli = commandMapCli;
    }

    /**
     * @param server
     * @throws UnsupportedOperationException always
     */
    @Override
    public void exec(String server) throws CliException {
        throw new UnsupportedOperationException("can't exec");
    }

    @Override
    public CliCommand parse(String[] cmdArgs) throws CliException {
        if (cmdArgs.length < 0) {
            throw new CliException("not enough arguments");
        }
        String cmdArg = cmdArgs[0];
        String[] args = new String[cmdArgs.length - 1];
        System.arraycopy(cmdArgs, 1, args, 0, cmdArgs.length - 1);
        return determineCommand(args, cmdArg);
    }

    protected CliCommand determineCommand(String[] args, String cmd) throws CliException {
        CliCommand cliCmd = commandMapCli.get(cmd);
        if (cliCmd != null) {
            return cliCmd.parse(args);
        } else {
            throw new CliException("unknown command");
        }
    }
}
