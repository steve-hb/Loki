package de.stvehb.loki.parser;

import de.stvehb.loki.core.ast.source.*;
import de.stvehb.loki.core.ast.source.Enum;
import de.stvehb.loki.core.util.Naming;
import de.stvehb.loki.core.ast.Author;
import de.stvehb.loki.core.ast.Project;
import de.stvehb.loki.core.ast.ProjectInfo;
import de.stvehb.loki.core.ast.depenendency.Dependency;
import de.stvehb.loki.core.generated.apidoc.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ASTGenerator {

	private static final Logger LOGGER = LoggerFactory.getLogger(ASTGenerator.class.getSimpleName());

	private static final List<Type> JAVA_TYPES = Arrays.asList(
		new BuiltInType("string"),
		new BuiltInType("boolean"),
		new BuiltInType("long"),
		new BuiltInType("object")
	);

	public static Project generate(Service service) {
		LOGGER.info("Generating basic AST...");
		Project project = new Project(
			generateProjectInfo(service),
			generateTypes(service),
			generateDependencies(service)
		);

		LOGGER.info("Connecting all elements...");
		for (Type type : project.getTypes()) type.setParent(project);
		project.getInfo().setParent(project);
		project.getInfo().getAuthor().setParent(project.getInfo());
		project.getTypes().stream().filter(type -> type instanceof Model).map(type -> (Model) type)
			.forEach(model -> model.getFields().forEach(field -> field.setParent(model)));

		return project;
	}

	private static ProjectInfo generateProjectInfo(Service service) {
		return new ProjectInfo(service.getName(), service.getVersion(), new Author(
				service.getInfo().getContact().getName(),
				service.getInfo().getContact().getEmail(),
				new String[]{} //TODO
			)
		);
	}

	@SuppressWarnings("OptionalGetWithoutIsPresent")
	private static List<Type> generateTypes(Service service) {
		LOGGER.debug("Type generation phase 1: signature generation");
		List<Type> types = new ArrayList<>();
		types.addAll(JAVA_TYPES);//TODO: The generator/compiler should add types

		Arrays.stream(service.getModels()).forEach(m -> types.add(new Model(m.getName(), service.getNamespace())));
		Arrays.stream(service.getEnums()).forEach(e -> types.add(new Enum(e.getName(), service.getNamespace())));

		LOGGER.debug("Type generation phase 2: generating fields with type bindings");

		LOGGER.debug("Model generation...");
		Arrays.stream(service.getModels()).forEach(_model -> {
			Model model = (Model) types.stream().filter(m -> m.getName().equals(_model.getName())).findFirst().get();

			Arrays.stream(_model.getFields()).forEach(_field -> {
				LOGGER.debug("Adding field to {} with type {} and name {}", model.getName(), _field.getType(), _field.getName());
				Type type = types.stream().filter(m -> m.getName().equals(Naming.extractType(_field.getType()))).findFirst().get();

				Field field = new Field(type, Naming.isArrayType(_field.getType()), Naming.extractType(_field.getName()));
				if (_field.getAnnotations() != null) {
					for (String _annotation : _field.getAnnotations()) {
						field.getAnnotations().add(new Annotation(_annotation, "TODO"));
					}
				}
				model.getFields().add(field);
			});
		});

		LOGGER.debug("Enum generation...");
		Arrays.stream(service.getEnums()).forEach(_enum -> {
			Enum model = (Enum) types.stream().filter(_m -> _m.getName().equals(_enum.getName())).findFirst().get();

			if (_enum.getAttributes() != null) {
				Arrays.stream(_enum.getAttributes()).forEach(_attributes -> {
					Type type = types.stream().filter(_m -> _m.getName().equals(_attributes.getName())).findFirst().get();

					model.getFields().add(
						new Field(type, false, _attributes.getName())
					);
				});
			}
		});

		return types;
	}

	private static List<Dependency> generateDependencies(Service service) {
		return new ArrayList<>(); //TODO
	}

}
