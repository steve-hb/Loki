package de.stvehb.loki.core.ast;

import de.stvehb.loki.core.ast.depenendency.Dependency;
import de.stvehb.loki.core.ast.source.Enum;
import de.stvehb.loki.core.ast.source.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project extends Element {

	private ProjectInfo info;
	private List<CompilerOption> compilerOptions;
	private List<Model> models;
	private List<Enum> enums;
	private List<Dependency> dependencies;

}
