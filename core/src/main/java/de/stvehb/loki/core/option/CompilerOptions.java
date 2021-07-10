package de.stvehb.loki.core.option;

import de.stvehb.loki.core.ast.CompilerOption;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompilerOptions {

	public static Optional<CompilerOption> getOption(Context context, String compiler, String key) {
		List<CompilerOption> compilerOptions = context.getProject().getCompilerOptions();
		if (compilerOptions == null) compilerOptions = new ArrayList<>();

		return compilerOptions.stream()
			.filter(compilerOption -> compilerOption.getCompiler().equalsIgnoreCase(compiler))
			.filter(compilerOption -> compilerOption.getKey().equalsIgnoreCase(key))
			.findAny();
	}
}
