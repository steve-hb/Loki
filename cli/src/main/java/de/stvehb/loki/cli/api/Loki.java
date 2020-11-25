package de.stvehb.loki.cli.api;

import de.stvehb.loki.core.ast.Project;
import de.stvehb.loki.core.ast.source.Model;
import de.stvehb.loki.core.generated.apidoc.Service;
import de.stvehb.loki.parser.ASTGenerator;
import de.stvehb.loki.parser.LokiParser;
import de.stvehb.loki.generator.java.generate.phases.*;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Path;
import java.util.Map;

/**
 * This class is an API for loading and processing Loki related formats and data.
 */
public class Loki {

	private static final Logger LOGGER = LoggerFactory.getLogger(Loki.class.getSimpleName());

	/**
	 * Loads the given <i>resource</i> and decodes it to an instance of {@link Service}.
	 *
	 * @param resource The resource name
	 * @return an instance of {@link Service}
	 */
	public static Service loadApiBuilderServiceFromResource(String resource) {
		LOGGER.info("Loading APIBuilder service from resource...");
		LOGGER.debug("Resource: {}", resource);

		Service service = LokiParser.loadFromResource(resource);

		LOGGER.debug("Successfully loaded APIBuilder service!");
		return service;
	}

	/**
	 * Loads the given <i>resource</i> and decodes it to an instance of {@link Service}.
	 *
	 * @param path The path of the api builder or <i>Loki</i> file
	 * @return an instance of {@link Service}
	 */
	public static Service loadApiBuilderService(Path path) {
		LOGGER.info("Loading APIBuilder service...");
		LOGGER.debug("Path: {}", path);

		Service service = LokiParser.load(path);

		LOGGER.debug("Successfully loaded APIBuilder service!");
		return service;
	}

	/**
	 * Loads the given <i>resource</i> and decodes it to an instance of {@link Service}.
	 *
	 * @param content The raw content used to generate an {@link Service} instance
	 * @return an instance of {@link Service}
	 */
	public static Service loadApiBuilderService(String content) {
		LOGGER.info("Loading APIBuilder service...");
		LOGGER.debug("Content given - output reduced");

		Service service = LokiParser.load(content);

		LOGGER.debug("Successfully loaded APIBuilder service!");
		return service;
	}

	/**
	 * Converts an abstract syntax tree (AST) from the given {@link Service} instance.
	 *
	 * @param service A {@link Service} instance as the source of truth
	 * @return an instance of {@link Project}
	 */
	public static Project convertApiBuilderToAST(Service service) {
		Project project = ASTGenerator.generate(service);
		LOGGER.info("Successfully generated abstract syntax tree!");
		return project;
	}

	public static void process(Project project) {

	}

	/**
	 * Regenerates the api builder models used by this project.
	 * Only execute this in the root directory of the project!
	 */
	@SneakyThrows
	private static void regenerateApiBuilderModels() {
		Service service = Loki.loadApiBuilderService(new File("./apibuilder.json").toPath());
		Project project = convertApiBuilderToAST(service);

		// Override namespace
		String namespace = "de.stvehb.loki.core.generated.apidoc";
		project.getInfo().setNamespace(namespace);
		project.getTypes().forEach(type -> type.setNamespace(namespace));

		PreProcessLintingPhase.process(project);
		GenerationTagPhase.process(project);
		LombokifierPhase.process(project);

		Map<Model, String> modelContents = ModelGenerationPhase.process(project);
		File targetDirectoryFile = new File("./target/");
		targetDirectoryFile.mkdir();

		ResourceOutputPhase.processModels(targetDirectoryFile.toPath(), modelContents);
	}

	public static void main(String[] args) {
		regenerateApiBuilderModels();
	}

}