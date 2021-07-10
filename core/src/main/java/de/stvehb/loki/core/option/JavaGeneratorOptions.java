package de.stvehb.loki.core.option;

import de.stvehb.loki.core.ast.CompilerOption;

public class JavaGeneratorOptions {

	public static boolean useListForArrays(Context context) {
		return Boolean.parseBoolean(CompilerOptions.getOption(context, "java", "useListsForArrays")
			.orElse(new CompilerOption(null, null, "false")).getValue());
	}

}
