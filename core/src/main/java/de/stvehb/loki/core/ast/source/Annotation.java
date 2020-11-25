package de.stvehb.loki.core.ast.source;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Annotation extends Type {

	private Map<String, String> values = new HashMap<>();

	public Annotation(String name, String namespace) {
		super(name, namespace);
	}

	public Annotation addValue(String key, String value) {
		this.getValues().put(key, value);
		return this;
	}
}
