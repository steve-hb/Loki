package de.stvehb.loki.core.ast.factory;

import de.stvehb.loki.core.ast.source.Field;
import de.stvehb.loki.core.ast.source.Type;

public class FieldFactory {

	public static Field create(Type type, boolean array, String name, String documentation) {
		return new Field(null, type, array, null, name, documentation);
	}

}
