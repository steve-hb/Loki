package de.stvehb.loki.generator.java.generate.phases;

import de.stvehb.loki.core.ast.Project;
import de.stvehb.loki.core.ast.source.Type;
import de.stvehb.loki.core.option.Context;
import de.stvehb.loki.core.util.ModelUtil;
import de.stvehb.loki.core.util.Naming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This phase will be executed before the AST gets processed by the Java generator. It's responsible for escaping illegal
 * field or class names and compliance with general Java code style guidelines (e.g. field and class naming).
 */
public class PreProcessLintingPhase {

	private static final Logger LOGGER = LoggerFactory.getLogger(PreProcessLintingPhase.class.getSimpleName());

	public static void process(Context context, Project project) {
		//TODO: [string] for arrays and string=>string for maps
		LOGGER.info("Start linting all models...");

		LOGGER.debug("==> Apply Java naming conventions to classes and enums");
		for (Type type : ModelUtil.projectTypes(project))
			if (!type.isBuiltIn()) type.setName(Naming.toJavaClass(type.getName()));

		LOGGER.debug("==> Apply escaping and naming strategy to fields");
		ModelUtil.modelTypeStream(project).forEach(model -> model.getFields().forEach(field ->
			field.setName(
				Naming.escapeReservedKeywords(
					field.getName()
				)
			)
		));

		LOGGER.debug("==> Correct built-in types (string -> String)");
		ModelUtil.fieldTypes(project).forEach(type -> type.setName(Naming.correctBuiltIntTypes(type.getName())));

		LOGGER.debug("==> Apply naming convention for all types (string -> String, built_in_type -> BuiltInType)");
		ModelUtil.projectTypes(project).forEach(type -> type.setName(Naming.toJavaClass(type.getName())));
	}

}
