package de.stvehb.loki.core.ast.source;

import de.stvehb.loki.core.ast.Element;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Field extends Element {

	private List<Annotation> annotations = new ArrayList<>();

	private Type type;
	private boolean array;
	private String name;
	private String documentation;

	public Field(Type type, boolean array, String name, String documentation) {
		this.type = type;
		this.array = array;
		this.name = name;
		this.documentation = documentation;
	}

}
