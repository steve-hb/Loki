package de.stvehb.loki.core.option;

import de.stvehb.loki.core.ast.Project;
import de.stvehb.loki.core.ast.source.Type;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Context {

	private Project project;
	private final List<Type> types = new ArrayList<>();
	private DebugOptions debugOptions;

	private final DebuggingStore debuggingStore = new DebuggingStore();

}
