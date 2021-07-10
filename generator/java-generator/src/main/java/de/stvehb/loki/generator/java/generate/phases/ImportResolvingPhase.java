package de.stvehb.loki.generator.java.generate.phases;

import de.stvehb.loki.core.ast.Project;
import de.stvehb.loki.core.ast.source.Annotation;
import de.stvehb.loki.core.ast.source.Field;
import de.stvehb.loki.core.ast.source.Model;
import de.stvehb.loki.core.ast.source.Type;
import de.stvehb.loki.core.option.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ImportResolvingPhase {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImportResolvingPhase.class.getSimpleName());

	public static void process(Context context, Project project) {
		project.getModels().forEach(model -> {
			model.setImports(generateImports(model));
			LOGGER.debug("Imports of {}: {}", model.getName(), model.getImports());
		});
	}

	private static List<String> generateImports(Model model) {
		Stream<Type> fieldTypes = findFieldTypes(model);
		Stream<Type> annotationTypes = findAnnotationTypes(model);
		LOGGER.debug("Field types of {}: {}", model.getName(), fieldTypes.collect(Collectors.toList()));
		LOGGER.debug("Annotation types of {}: {}", model.getName(), annotationTypes.collect(Collectors.toList()));

		return Stream.concat(findFieldTypes(model), findAnnotationTypes(model)).distinct() // Remove duplicates
			.map(type -> renderFullyQualifiedClassName((Project) model.getParent(), type))
			.collect(Collectors.toList());
	}

	private static Stream<Type> findFieldTypes(Model model) {
		return getAllFieldTypes(model)
			.filter(type -> !type.isBuiltIn());
	}

	/**
	 * Finds all {@link Annotation}s in the given {@link Model}.
	 * These {@link Annotation}s are Types themselves and can occur at the {@link Model} and the
	 * {@link Field} level.
	 *
	 * @param model the model to search for annotations.
	 * @return the types used as annotations in the model.
	 */
	private static Stream<Type> findAnnotationTypes(Model model) {
		return Stream.concat(
			model.getAnnotations().stream(),
			model.getFields().stream().filter(field -> Objects.nonNull(field.getAnnotations()))
				.flatMap(field -> field.getAnnotations().stream())
		);
	}

	/**
	 * Returns all {@link Type}s used by {@link de.stvehb.loki.core.ast.source.Field}s in the given {@link Model}
	 * including their map-value.
	 *
	 * @param model the model containing the fields.
	 * @return all {@link Type}s used by the model's {@link de.stvehb.loki.core.ast.source.Field}s.
	 */
	private static Stream<Type> getAllFieldTypes(Model model) {
		return model.getFields().stream()
			.flatMap(field -> Stream.of(
				field.getType(),
				field.getMapValueType()
			).filter(Objects::nonNull));
	}

	private static String renderFullyQualifiedClassName(Project project, Type type) {
		return project.getInfo().getNamespace() + "." + type.getName();
	}

}
