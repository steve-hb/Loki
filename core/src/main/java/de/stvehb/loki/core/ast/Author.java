package de.stvehb.loki.core.ast;

import lombok.Data;

@Data
public class Author extends Element {

	private String name;
	private String email;
	private String[] roles;

	public Author(String name, String email, String[] roles) {
		this.name = name;
		this.email = email;
		this.roles = roles;
	}
}
