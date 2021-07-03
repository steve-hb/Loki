package de.stvehb.loki.core.ast.source;

import lombok.Data;

/**
 * Represents a built-in type of a language.
 * Example: all native/primitive types in Java (int, short, float etc.)
 */
@Data
public class BuiltInType extends Type {

	public BuiltInType(String name) {
		super(name, null, null);
	}

	@Override
	public boolean isBuiltIn() {
		return true;
	}

}
