package de.stvehb.loki.core.util;

import de.stvehb.loki.core.ast.Project;
import de.stvehb.loki.core.ast.source.Model;
import de.stvehb.loki.core.ast.source.Type;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModelUtil {

	/**
	 * Returns only models and enums, no built-in types.
	 */
	public static List<Model> models(Project project) {
		return modelTypeStream(project).collect(Collectors.toList());
	}

	/**
	 * Returns only models and enums, no built-in types.
	 */
	public static Stream<Model> modelTypeStream(Project project) {
		return typeStream(project).filter(type -> type instanceof Model).map(type -> (Model) type);
	}

	public static Stream<Type> typeStream(Project project) {
		return Stream.concat(
			project.getModels().stream(),
			project.getEnums().stream()
		);
	}

	public static List<Type> types(Project project) {
		return typeStream(project).collect(Collectors.toList());
	}

}
