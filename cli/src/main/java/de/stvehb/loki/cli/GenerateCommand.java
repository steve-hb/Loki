package de.stvehb.loki.cli;

import picocli.CommandLine;

import java.nio.file.Path;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "generate", description = "Generates source code from the given input.")
public class GenerateCommand implements Callable<Integer> {

	@CommandLine.Parameters(index = "0", description = "The target directory where the files should be generated. Usually the \"src/main/java\" directory of your project")
	private Path targetDirectory;

	@Override
	public Integer call() throws Exception {
		return null;
	}
}
