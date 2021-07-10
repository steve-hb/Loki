package de.stvehb.loki.generator.java.generate.phases;

import de.stvehb.loki.core.ast.Project;
import de.stvehb.loki.core.ast.source.Type;
import de.stvehb.loki.core.option.Context;
import de.stvehb.loki.generator.java.generate.JavaTypes;
import de.stvehb.loki.generator.java.generate.TypeResolveException;

import java.util.Optional;

public class TypeResolvingPhase {

	/**
	 * This phase resolves the types used in the project.
	 *
	 * @param context the context.
	 * @param project the project.
	 */
	public static void process(Context context, Project project) {
		// First add all the models and enums to the available types
		registerProvidedTypes(context, project);

		// Check if all types required are available and set them to the instances managed by the context
		project.getModels().forEach(model -> {
			model.getAnnotations().forEach(annotation -> {
			});

			model.getFields().forEach(field -> {
				// Get global instance of type
				Type type = getType(context, field.getType()).orElseThrow(() ->
					new TypeResolveException("The requested type " + field.getType() + " cannot be resolved."));

				// Set global type instance
				field.setType(type);

				// Map-value
				if (field.getMapValueType() != null) {
					// Get global instance of type
					Type mapValueType = getType(context, field.getType()).orElseThrow(() ->
						new TypeResolveException("The requested type " + field.getType() + " cannot be resolved."));

					// Set global type instance
					field.setMapValueType(mapValueType);
				}
			});
		});
	}

	private static void registerProvidedTypes(Context context, Project project) {
		project.getModels().forEach(model -> context.getTypes().add(model));
		project.getEnums().forEach(model -> context.getTypes().add(model));
	}

	private static Optional<Type> getType(Context context, Type requested) {
		Optional<Type> optionalType = JavaTypes.BUILT_IN_TYPES.stream()
			.filter(type -> type.getName().equalsIgnoreCase(requested.getName())).findAny();
		if (optionalType.isPresent()) return optionalType;

		return context.getTypes().stream().filter(type -> type.getName().equalsIgnoreCase(requested.getName())).findAny();
	}

}
