package de.stvehb.loki.cli.api;

import de.stvehb.loki.core.ast.Project;
import de.stvehb.loki.core.ast.source.Model;
import de.stvehb.loki.core.ast.source.Type;
import de.stvehb.loki.core.generated.apidoc.Service;
import de.stvehb.loki.core.option.DebugOptions;
import de.stvehb.loki.core.option.Context;
import de.stvehb.loki.core.util.ModelUtil;
import de.stvehb.loki.generator.java.generate.JavaGenerator;
import de.stvehb.loki.parser.ASTGenerator;
import de.stvehb.loki.parser.ApidocParser;
import de.stvehb.loki.generator.java.generate.phases.*;
import de.stvehb.loki.parser.LokiParser;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;

/**
 * This class is an API for loading and processing Loki related formats and data.
 */
public class Loki {

	private static final Logger LOGGER = LoggerFactory.getLogger(Loki.class.getSimpleName());

	/**
	 * Loads the given <i>resource</i> and decodes it to an instance of {@link Project}.
	 *
	 * @param resource The resource name
	 * @return an instance of {@link Project}
	 */
	public static Project loadLokiProjectFromResource(String resource) {
		LOGGER.info("Loading Loki project from resource...");
		LOGGER.debug("Resource: {}", resource);

		Project project = LokiParser.loadFromResource(resource);
		cleanupProject(project);

		LOGGER.debug("Successfully loaded Loki project!");
		return project;
	}

	/**
	 * Loads the given <i>resource</i> and decodes it to an instance of {@link Project}.
	 *
	 * @param path The path of the <i>Loki</i> file
	 * @return an instance of {@link Project}
	 */
	public static Project loadLokiProject(Path path) {
		LOGGER.info("Loading Loki project...");
		LOGGER.debug("Path: {}", path);

		Project project = LokiParser.load(path);
		cleanupProject(project);

		LOGGER.debug("Successfully loaded Loki project!");
		return project;
	}

	/**
	 * Loads the given <i>resource</i> and decodes it to an instance of {@link Project}.
	 *
	 * @param content The raw content used to generate an {@link Project} instance
	 * @return an instance of {@link Project}
	 */
	public static Project loadLokiProject(String content) {
		LOGGER.info("Loading Loki project...");
		LOGGER.debug("Content given - output reduced");

		Project project = LokiParser.load(content);
		cleanupProject(project);

		LOGGER.debug("Successfully loaded Loki project!");
		return project;
	}

	/**
	 * Loads the given <i>resource</i> and decodes it to an instance of {@link Service}.
	 *
	 * @param resource The resource name
	 * @return an instance of {@link Service}
	 */
	public static Service loadApiBuilderServiceFromResource(String resource) {
		LOGGER.info("Loading APIBuilder service from resource...");
		LOGGER.debug("Resource: {}", resource);

		Service service = ApidocParser.loadFromResource(resource);

		LOGGER.debug("Successfully loaded APIBuilder service!");
		return service;
	}

	/**
	 * Loads the given <i>resource</i> and decodes it to an instance of {@link Service}.
	 *
	 * @param path The path of the api builder
	 * @return an instance of {@link Service}
	 */
	public static Service loadApiBuilderService(Path path) {
		LOGGER.info("Loading APIBuilder service...");
		LOGGER.debug("Path: {}", path);

		Service service = ApidocParser.load(path);

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

		Service service = ApidocParser.load(content);

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
		cleanupProject(project);
		LOGGER.info("Successfully generated abstract syntax tree!");
		return project;
	}

	private static void cleanupProject(Project project) {
		if (project.getModels() == null) project.setModels(new ArrayList<>());
		if (project.getEnums() == null) project.setEnums(new ArrayList<>());

		LOGGER.debug("Connecting all elements...");
		for (Type type : ModelUtil.models(project)) type.setParent(project);

		project.getInfo().setParent(project);
		project.getInfo().getAuthor().setParent(project.getInfo());

		ModelUtil.models(project).forEach(model -> model.getFields().forEach(field -> field.setParent(model)));
	}

	public static void process(Project project) {
		Context context = new Context(
			project,
			new DebugOptions(true)
		);

		project.getModels().forEach(type -> type.setNamespace(project.getInfo().getNamespace())); //TODO: Workaround
		project.getEnums().forEach(type -> type.setNamespace(project.getInfo().getNamespace()));

		JavaGenerator.process(context, project);
	}

	/**
	 * Regenerates the api builder models used by this project.
	 * Only execute this in the root directory of the project!
	 */
	@SneakyThrows
	private static void regenerateApiBuilderModels() {
		Service service = Loki.loadApiBuilderService(new File("./apibuilder.json").toPath());
		Project project = convertApiBuilderToAST(service);

		Context context = new Context(
			project,
			new DebugOptions(true)
		);

		// Override namespace
		String namespace = "de.stvehb.loki.core.generated.apidoc";
		project.getInfo().setNamespace(namespace);
		project.getModels().forEach(type -> type.setNamespace(namespace));
		project.getEnums().forEach(type -> type.setNamespace(namespace));

		LintingPhase.process(context, project);
		GenerationTagPhase.process(context, project);
		LombokifierPhase.process(context, project);

		Map<Model, String> modelContents = RenderingPhase.process(context, project);
		File targetDirectoryFile = new File("./target/");
		targetDirectoryFile.mkdir();

		ResourceOutputPhase.processModels(targetDirectoryFile.toPath(), modelContents);
	}

	private static void loadLokiModels() {
		Project project = loadLokiProjectFromResource("todo-example.loki.json");
		System.out.println(project);
		process(project);
	}

	public static void main(String[] args) {
		loadLokiModels();
	}

}
