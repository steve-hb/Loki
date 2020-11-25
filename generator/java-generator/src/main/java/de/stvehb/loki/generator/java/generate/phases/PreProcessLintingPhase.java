package de.stvehb.loki.generator.java.generate.phases;

import de.stvehb.loki.core.ast.Project;
import de.stvehb.loki.core.ast.source.Type;
import de.stvehb.loki.core.util.Naming;
import de.stvehb.loki.core.util.ModelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

/**
 * This phase will be executed before the AST gets processed by the Java generator. It's responsible for escaping illegal
 * field or class names and compliance with general Java code style guidelines (e.g. field and class naming).
 */
public class PreProcessLintingPhase {

	private static final Logger LOGGER = LoggerFactory.getLogger(PreProcessLintingPhase.class.getSimpleName());

	public static void process(Project project) {
		LOGGER.info("Start linting all models...");

		LOGGER.debug("==> Apply Java naming conventions to classes and enums");
		for (Type type : ModelUtil.types(project)) if(!type.isBuiltIn()) type.setName(Naming.toJavaClass(type.getName()));

		LOGGER.debug("==> Apply escaping and naming strategy to fields");
		ModelUtil.modelTypeStream(project).forEach(model -> model.getFields().forEach(field ->
			field.setName(Naming.escapeReservedKeywords(field.getName()))
		));

		LOGGER.debug("==> Apply naming convention for all types (string -> String, built_in_type -> BuiltInType)");
		ModelUtil.types(project).forEach(type -> type.setName(Naming.toJavaClass(type.getName())));
	}

}
