package de.stvehb.loki.generator.java.generate;

import de.stvehb.loki.core.ast.source.BuiltInType;
import de.stvehb.loki.core.ast.source.Type;

import java.util.Arrays;
import java.util.List;

public class JavaTypes {

	public static final List<Type> BUILT_IN_TYPES = Arrays.asList(
		new BuiltInType("String"),
		new BuiltInType("boolean"),
		new BuiltInType("long"),
		new BuiltInType("Object")
	);

	public static final Type LIST = new Type("List", "java.util", null);
	public static final Type MAP = new Type("Map", "java.util", null);

}
