package de.stvehb.loki.core.ast;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompilerOption {

	private String compiler;
	private String key;
	private String value;

}
