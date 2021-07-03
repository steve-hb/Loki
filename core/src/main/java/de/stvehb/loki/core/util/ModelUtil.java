package de.stvehb.loki.core.util;

import de.stvehb.loki.core.ast.Project;
import de.stvehb.loki.core.ast.source.Field;
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

	/**
	 * Returns the types provided by the project itself - no higher order types like Java built-in types.
	 *
	 * @param project the project from where to extract the types.
	 * @return a stream of all types provided by the given project.
	 */
	public static Stream<Type> typeStream(Project project) {
		return Stream.concat(
			project.getModels().stream(),
			project.getEnums().stream()
		);
	}

	/**
	 * Returns the types provided by the project itself - no higher order types like Java built-in types.
	 *
	 * @param project the project from where to extract the types.
	 * @return a list of all types provided by the given project.
	 */
	public static List<Type> projectTypes(Project project) {
		return typeStream(project).collect(Collectors.toList());
	}

	/**
	 * Returns all types used by fields contained by models of the given project.
	 *
	 * @param project the project to search in for fields and their types.
	 * @return
	 */
	public static Stream<Type> fieldTypes(Project project) {
		return project.getModels().stream().map(Model::getFields).flatMap(
			fields -> fields.stream().map(Field::getType)
		).distinct();
	}
}
