package de.stvehb.loki.core.option;

import de.stvehb.loki.core.ast.CompilerOption;

import java.util.Optional;

public class CompilerOptions {

	public static Optional<CompilerOption> getOption(Context context, String compiler, String key) {
		return context.getProject().getCompilerOptions().stream()
			.filter(compilerOption -> compilerOption.getCompiler().equalsIgnoreCase(compiler))
			.filter(compilerOption -> compilerOption.getKey().equalsIgnoreCase(key))
			.findAny();
	}
}
