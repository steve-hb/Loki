package de.stvehb.loki.core.ast;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Just a helper class to represent an element with a parent element.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Element {

	private Element parent;

}
