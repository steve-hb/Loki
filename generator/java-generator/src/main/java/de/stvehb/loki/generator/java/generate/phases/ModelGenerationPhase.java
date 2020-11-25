package de.stvehb.loki.generator.java.generate.phases;

import de.stvehb.loki.core.ast.Project;
import de.stvehb.loki.core.ast.source.Model;
import de.stvehb.loki.generator.java.generate.phases.render.ModelRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Generates the models & enums from the AST to final classes.
 */
public class ModelGenerationPhase {

	private static final Logger LOGGER = LoggerFactory.getLogger(ModelGenerationPhase.class.getSimpleName());

	public static Map<Model, String> process(Project project) {
		return project.getTypes().stream()
			.filter(type -> !type.isBuiltIn()) // Don't render built-in types
			.filter(type -> type instanceof Model) // This phase is only responsible for models
			.map(type -> (Model) type)
			.map(model -> new AbstractMap.SimpleEntry<>(model, ModelRenderer.render(project, model))) // Render the models
			.collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
	}

}
