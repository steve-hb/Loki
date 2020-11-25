package de.stvehb.loki.generator.java.generate.phases.render;

import de.stvehb.loki.core.ast.source.Model;
import java.util.stream.Collectors;

public class ImportRenderer {

	public static String render(Model model) {
		return model.getImports().stream()
			.map(_import -> "import " + _import + ModelRenderer.EOL)
			.collect(Collectors.joining());
	}

}
