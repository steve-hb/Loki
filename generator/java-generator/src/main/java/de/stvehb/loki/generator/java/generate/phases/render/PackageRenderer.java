package de.stvehb.loki.generator.java.generate.phases.render;

import de.stvehb.loki.core.ast.Project;

public class PackageRenderer {

	public static String render(Project project) {
		if (project.getInfo().getNamespace() == null) return "";
		return "package " + project.getInfo().getNamespace() + ModelRenderer.EOL;
	}

}
