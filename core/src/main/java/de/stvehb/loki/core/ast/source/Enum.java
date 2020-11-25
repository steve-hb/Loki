package de.stvehb.loki.core.ast.source;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Enum extends Model {

	public Enum(String name, String namespace) {
		super(name, namespace);
	}

}
