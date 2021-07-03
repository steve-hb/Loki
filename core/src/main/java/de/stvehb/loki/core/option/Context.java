package de.stvehb.loki.core.option;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Context {

	private DebugOptions debugOptions;

	private final DebuggingStore debuggingStore = new DebuggingStore();

}
