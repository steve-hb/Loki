package de.stvehb.loki.generator.java.generate.phases;

import de.stvehb.loki.core.ast.Project;
import de.stvehb.loki.core.ast.source.Annotation;
import de.stvehb.loki.core.ast.source.Model;
import de.stvehb.loki.generator.java.generate.JavaGenerator;

import java.util.Arrays;
import java.util.List;

/**
 * This phase adds generation tags to the models.
 */
public class GenerationTagPhase {

	private static final List<Annotation> DEFAULT_ANNOTATIONS = Arrays.asList(
		new Annotation("Generated", "javax.annotation.processing.Generated")
			.addValue("value", "\"" + JavaGenerator.class.getCanonicalName() + "\"")
	);

	public static void process(Project project) {
		project.getTypes().stream()
			.filter(type -> type instanceof Model).map(type -> (Model) type)
			.forEach(model -> model.getAnnotations().addAll(DEFAULT_ANNOTATIONS));
	}

}
