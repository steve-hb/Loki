package de.stvehb.loki.generator.java.generate.phases.render;

import de.stvehb.loki.core.ast.source.Annotation;

import java.util.stream.Collectors;

public class AnnotationRenderer {

	public static String render(Annotation annotation) {
		return "@" + annotation.getName() + "(" + renderValues(annotation) + ")";
	}

	private static String renderValues(Annotation annotation) {
		return annotation.getValues().entrySet()
			.stream()
			.map(entry -> entry.getKey() + " = " + entry.getValue())
			.collect(Collectors.joining());
	}

}
