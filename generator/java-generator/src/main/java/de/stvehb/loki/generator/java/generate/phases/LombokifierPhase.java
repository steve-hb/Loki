package de.stvehb.loki.generator.java.generate.phases;

import de.stvehb.loki.core.ast.Project;
import de.stvehb.loki.core.ast.source.Annotation;
import de.stvehb.loki.core.ast.source.Model;

import java.util.Arrays;
import java.util.List;

/**
 * This phase adds @Data and @AllArgsConstructor to every model.
 */
public class LombokifierPhase {

	private static final List<Annotation> DEFAULT_ANNOTATIONS = Arrays.asList(
		new Annotation("Data", "lombok"),
		new Annotation("AllArgsConstructor", "lombok")
	);

	public static void process(Project project) {
		project.getTypes().stream()
			.filter(type -> type.getClass() == Model.class).map(type -> (Model) type)
			.forEach(model -> model.getAnnotations().addAll(DEFAULT_ANNOTATIONS));
	}

}
