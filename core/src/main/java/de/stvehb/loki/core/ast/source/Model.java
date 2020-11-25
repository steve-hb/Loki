package de.stvehb.loki.core.ast.source;

import de.stvehb.loki.core.ast.Project;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Model extends Type {

	private List<Annotation> annotations = new ArrayList<>();
	private List<Field> fields = new ArrayList<>();

	public Model(String name, String namespace) {
		super(name, namespace);
	}

	/**
	 * Returns a list with all imports for this model including enums, annotations and other models.
	 * Example: ["com.example.Hello", "com.example.World"]
	 *
	 * @return a list with all imports
	 */
	public List<String> getImports() {
		//TODO: Annotations
		return Stream.concat(
			Stream.concat(
				this.getFields().stream()
					.map(Field::getType)
					.filter(type -> !type.isBuiltIn())
					.map(Type::getName)
					.filter(s -> ((Project) this.getParent()).getInfo().getNamespace() != null)//TODO: This is a dirty workaround
					.map(s -> ((Project) this.getParent()).getInfo().getNamespace() + "." + s),
				this.getFields().stream().flatMap(field -> field.getAnnotations().stream()
					.filter(annotation -> annotation.getNamespace() != null)
					.map(annotation -> annotation.getNamespace() + "." + annotation.getName())
				)
			),
			this.getAnnotations()
				.stream()
				.filter(annotation -> annotation.getNamespace() != null)
				.map(annotation -> annotation.getNamespace() + "." + annotation.getName())
		).collect(Collectors.toList());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Model model = (Model) o;
		return Objects.equals(annotations, model.annotations) &&
			Objects.equals(fields, model.fields);
	}

	@Override
	public int hashCode() {
		return this.getName().hashCode();
	}

}
