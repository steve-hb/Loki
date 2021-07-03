package de.stvehb.loki.generator.java.generate.phases.render;

import de.stvehb.loki.core.ast.source.Field;

public class FieldRenderer {

	public static String render(Field field) {
		String content = "privat ";

		if (field.getMapValueType() != null) content += "Map<";

		content += field.getType().getName();

		if (field.getMapValueType() != null) content += ">";
		if (field.isArray()) content += "[]";

		content += " ";
		content += field.getName();

		return content;
	}

}
