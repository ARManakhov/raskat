package dev.sirosh.raskat.cli.cli;

import dev.sirosh.raskat.cli.client.BackendAppClient;
import dev.sirosh.raskat.cli.client.RequestException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class AppGetCommand extends CliCommand {
    private CommandLine cl;
    private static Options options = new Options();

    static {
        options.addOption("name", true, "name of app");
    }

    public AppGetCommand() {
        super("get");
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
        try {
            if (cl.hasOption("name")) {
                new BackendAppClient().getApp(server, cl.getOptionValue("name"));
            } else {
                new BackendAppClient().getApps(server);
            }
        } catch (RequestException e) {
            throw new CliException(e.getMessage());
        }
    }
}
