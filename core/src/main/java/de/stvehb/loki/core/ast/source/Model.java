package de.stvehb.loki.core.ast.source;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Model extends Type {

	private List<Annotation> annotations = new ArrayList<>();
	private List<Field> fields = new ArrayList<>();
	private List<String> imports = new ArrayList<>();

	public Model(String name, String namespace, String documentation) {
		super(name, namespace, documentation);
	}

	public Model addField(Field field) {
		this.getFields().add(field);
		return this;
	}

	/**
	 * Returns a list with all imports for this model including enums, annotations and other models.
	 * Example: ["com.example.Hello", "com.example.World"]
	 *
	 * @return a list with all imports
	 */
	public List<String> getImports() {
		return this.imports;
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
