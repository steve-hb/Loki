package de.stvehb.loki.core.ast;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Author extends Element {

	private String name;
	private String email;
	private String[] roles;

}
