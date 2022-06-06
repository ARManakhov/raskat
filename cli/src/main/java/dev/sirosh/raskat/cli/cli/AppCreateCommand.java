package dev.sirosh.raskat.cli.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class AppCreateCommand extends CliCommand {
    private CommandLine cl;
    private static Options options = new Options();

    static {ск
        options.addOption("file", true, "file with configs");
    }

    public AppCreateCommand() {
        super("create");
    }

    @Override
    public CliCommand parse(String[] cmdArgs) throws CliException {
        DefaultParser parser = new DefaultParser();
        try {
            cl = parser.parse(options, cmdArgs);
        } catch (ParseException ex) {
            throw new CliParseException(ex);
        }
        return this;
    }

    @Override
    public void exec(String server) throws CliException {
        return false;
    }
}
