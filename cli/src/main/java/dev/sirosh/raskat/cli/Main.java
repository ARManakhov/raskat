package dev.sirosh.raskat.cli;

import dev.sirosh.raskat.cli.cli.CliException;
import dev.sirosh.raskat.cli.cli.CliParseException;
import dev.sirosh.raskat.cli.cli.RootCommand;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;


public class Main {
    static final String defaultServer = "localhost:8081";

    public static void main(String[] args) {
        if (args.length == 0) {
            usage();
            return;
        }

        try {
            new RootCommand().parse(args);
        } catch (CliException e) {
            System.err.println(e.getMessage());
        }
    }


    static String getServer(String[] args) throws CliParseException {
        Options options = new Options()
                .addOption("server", false, "server");
        try {
            CommandLine cl = new DefaultParser().parse(options, args, false);
            if (cl.hasOption("server")) {
                return cl.getOptionValue("server");
            } else {
                return defaultServer;
            }
        } catch (ParseException ex) {
            throw new CliParseException(ex);
        }
    }

    static void usage() {
        System.err.println("read documentation! oh wait ... it doesn't exists :P");
    }
}
