package de.stvehb.loki.core.option;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DebugOptions {

	/**
	 * Defines whether to put debug information at the end of every generated line.
	 * This allows for more traceability in relation to decisions made by the rendering or generation pipelines.
	 * Example:
	 * <p>
	 *     private String customerName; // field: private, string, customer_type; Transformation: built-in type string; Transformation: camelCase customer_Type
	 * </p>
	 */
	private boolean annotateLines;

}
