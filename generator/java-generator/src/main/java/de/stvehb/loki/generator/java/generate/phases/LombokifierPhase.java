package de.stvehb.loki.generator.java.generate.phases;

import de.stvehb.loki.core.ast.Project;
import de.stvehb.loki.core.ast.source.Annotation;
import de.stvehb.loki.core.ast.source.Model;
import de.stvehb.loki.core.option.Context;
import de.stvehb.loki.core.util.ModelUtil;

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

	public static void process(Context context, Project project) {
		ModelUtil.modelTypeStream(project)
			.filter(type -> type.getClass() == Model.class) // Enums are not allowed to have @Data annotations
			.forEach(model -> model.getAnnotations().addAll(DEFAULT_ANNOTATIONS));
	}

}
