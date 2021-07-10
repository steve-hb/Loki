package de.stvehb.loki.generator.java.generate;

import de.stvehb.loki.core.ast.Project;
import de.stvehb.loki.core.ast.source.Model;
import de.stvehb.loki.core.option.Context;
import de.stvehb.loki.generator.java.generate.phases.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Map;

public class JavaGenerator {

	private static final Logger LOGGER = LoggerFactory.getLogger(JavaGenerator.class.getSimpleName());

	public static void process(Context context, Project project) {
		long start = System.currentTimeMillis();

		printPhase("Type Resolving");
		TypeResolvingPhase.process(context, project);

		printPhase("Generation Tagging");
		GenerationTagPhase.process(context, project);

		printPhase("Lombokify");
		LombokifierPhase.process(context, project);

		printPhase("Import Resolving");
		ImportResolvingPhase.process(context, project);

		printPhase("Linting");
		LintingPhase.process(context, project);

		printPhase("Rendering");
		Map<Model, String> output = RenderingPhase.process(context, project);

		printPhase("Output");
		File targetDirectoryFile = new File("./target/");
		targetDirectoryFile.mkdir();
		ResourceOutputPhase.processModels(targetDirectoryFile.toPath(), output);

		LOGGER.info("Took {}ms", (System.currentTimeMillis() - start));
	}

	private static void printPhase(String phase) {
		String divider = "=".repeat(20);

		LOGGER.info(divider);
		LOGGER.info("=> {}", phase);
	}

}
