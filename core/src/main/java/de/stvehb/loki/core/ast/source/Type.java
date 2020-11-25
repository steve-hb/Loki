package de.stvehb.loki.core.ast.source;

import de.stvehb.loki.core.ast.Element;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Type extends Element {

	private String name;
	private String namespace;

	/**
	 * Return whether this type is a built-in type of the target language.
	 *
	 * @return whether this type is a built-in type
	 */
	public boolean isBuiltIn() {
		return false;
	}

}
