package de.stvehb.loki.core.ast.source;

import de.stvehb.loki.core.ast.Element;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Field extends Element {

	private List<Annotation> annotations;

	private Type type;
	private boolean array;
	private Type mapValueType;
	private String name;
	private String documentation;

}
