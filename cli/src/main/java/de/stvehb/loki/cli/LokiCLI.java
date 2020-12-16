package de.stvehb.loki.cli;

import picocli.CommandLine;

public class LokiCLI {

	public static void main(String[] args) {
		int exitCode = new CommandLine(new GenerateCommand()).execute(args);
		System.exit(exitCode);
	}

}
