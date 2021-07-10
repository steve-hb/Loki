package de.stvehb.loki.generator.java.generate.phases.render;

import de.stvehb.loki.core.ast.source.Model;
import de.stvehb.loki.core.option.Context;

import java.util.stream.Collectors;

public class ImportRenderer {

	public static String render(Context context, Model model) {
		return model.getImports().stream()
			.map(_import -> "import " + _import + ModelRenderer.EOL)
			.collect(Collectors.joining());
	}

}
