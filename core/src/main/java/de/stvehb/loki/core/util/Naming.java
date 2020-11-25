package de.stvehb.loki.core.util;

import java.util.Set;

public class Naming {

	// Some may be missing, but these should be the most important ones
	private static final Set<String> RESERVED_KEYWORDS = Set.of(
		"default", "native", "private", "public", "protected", "class",
		"import", "package", "static", "byte", "char", "short", "int",
		"long", "float", "double", "null", "false", "true", "void"
	);

	public static String escapeReservedKeywords(String s) {
		if (RESERVED_KEYWORDS.contains(s.toLowerCase())) return "_" + s;
		return s;
	}

	public static String toJavaClass(String s) {
		if (s.isEmpty()) return s;
		s = extractType(s);

		// Remove every underscore and capitalize the following word
		for (int i; (i = s.indexOf('_')) != -1;) {
			String first = s.substring(0, i);
			String second = s.substring(i + 1);
			s = first + Naming.capitalize(second);
		}

		return capitalize(s);
	}

	/**
	 * Removes array indicators from given input.
	 *
	 * @param s The raw input
	 * @return a clean type
	 */
	public static String extractType(String s) {
		if (s.isEmpty()) return s;
		if (isArrayType(s)) s = s.substring(1, s.length() - 1); // Remove array indicators
		return s;
	}

	public static boolean isArrayType(String s) {
		return s.charAt(0) == '[' && s.charAt(s.length() - 1) == ']';
	}

	private static String capitalize(String s) {
		if (s.isEmpty()) return s;
		return Character.toUpperCase(s.charAt(0)) + s.substring(1);
	}

}
