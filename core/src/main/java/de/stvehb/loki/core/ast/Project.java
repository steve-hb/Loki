package de.stvehb.loki.core.ast;

import de.stvehb.loki.core.ast.depenendency.Dependency;
import de.stvehb.loki.core.ast.source.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project extends Element {

	private ProjectInfo info;
	private List<Type> types; // Including enums, models; Excluding "default" types like "int" "double" etc.
	private List<Dependency> dependencies;

}
