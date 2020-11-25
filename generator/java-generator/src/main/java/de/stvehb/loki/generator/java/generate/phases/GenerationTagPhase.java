package de.stvehb.loki.generator.java.generate.phases;

import de.stvehb.loki.core.ast.Project;
import de.stvehb.loki.core.ast.source.Annotation;
import de.stvehb.loki.core.util.ModelUtil;
import de.stvehb.loki.generator.java.generate.JavaGenerator;

import java.util.Arrays;
import java.util.List;

/**
 * This phase adds generation tags to the models.
 */
public class GenerationTagPhase {

	private static final List<Annotation> DEFAULT_ANNOTATIONS = Arrays.asList(
		new Annotation("Generated", "javax.annotation.processing")
			.addValue("value", "\"" + JavaGenerator.class.getCanonicalName() + "\"")
	);

	public static void process(Project project) {
		ModelUtil.modelTypeStream(project).forEach(model -> model.getAnnotations().addAll(DEFAULT_ANNOTATIONS));
	}

}
