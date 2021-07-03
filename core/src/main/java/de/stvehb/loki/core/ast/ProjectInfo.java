package de.stvehb.loki.core.ast;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectInfo extends Element {

	private String name;
	private String version;
	private String namespace;
	private Author author;

}
