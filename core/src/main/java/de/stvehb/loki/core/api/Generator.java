package de.stvehb.loki.core.api;

import de.stvehb.loki.core.ast.Project;

import java.util.Map;

/**
 * Generates <i>output</i> from the given <i>project AST</i> using the <i>properties</i>.
 * The implementation is responsible for deciding how to handle custom properties and what the output should look like.
 */
public interface Generator {

	void process(Project project, Map<String, String> properties);

}
