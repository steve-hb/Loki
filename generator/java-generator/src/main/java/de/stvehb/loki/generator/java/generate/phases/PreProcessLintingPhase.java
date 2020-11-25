package de.stvehb.loki.generator.java.generate.phases;

import de.stvehb.loki.core.ast.Project;
import de.stvehb.loki.core.ast.source.Model;
import de.stvehb.loki.core.ast.source.Type;
import de.stvehb.loki.core.util.Naming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This phase will be executed before the AST gets processed by the Java generator. It's responsible for escaping illegal
 * field or class names and compliance with general Java code style guidelines (e.g. field and class naming).
 */
public class PreProcessLintingPhase {

	private static final Logger LOGGER = LoggerFactory.getLogger(PreProcessLintingPhase.class.getSimpleName());

	public static void process(Project project) {
		LOGGER.info("Start linting all models...");

		LOGGER.debug("==> Apply Java naming conventions to classes and enums");
		for (Type type : project.getTypes()) if(!type.isBuiltIn()) type.setName(Naming.toJavaClass(type.getName()));

		LOGGER.debug("==> Apply escaping and naming strategy to fields");
		project.getTypes().stream()
			.filter(type -> type instanceof Model).map(type -> (Model) type)
			.forEach(model -> model.getFields().forEach(field ->
				field.setName(Naming.escapeReservedKeywords(field.getName()))
			));

		LOGGER.debug("==> Apply naming convention for all types (string -> String, built_in_type -> BuiltInType)");
		project.getTypes().forEach(type -> type.setName(Naming.toJavaClass(type.getName())));
	}

}
