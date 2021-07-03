package de.stvehb.loki.core.option;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * This store is used for debugging information.
 * Every renderer and transformer shall be written to put changelogs into this store for later analysis.
 */
@Data
public class DebuggingStore {

	/**
	 * Store holding debug information for the current line.
	 */
	private final List<String> lineStore = new ArrayList<>();

}
