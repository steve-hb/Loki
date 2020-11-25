package de.stvehb.loki.core.ast;

import lombok.Data;

@Data
public class ProjectInfo extends Element {

	private String name;
	private String version;
	private String namespace; // "package" in Java //TODO: How to handle missing namespaces?
	private Author author;

	public ProjectInfo(String name, String version, Author author) {
		this.name = name;
		this.version = version;
		this.author = author;
	}
}
