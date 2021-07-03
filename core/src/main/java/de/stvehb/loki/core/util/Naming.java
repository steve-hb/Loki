package de.stvehb.loki.core.util;

import java.util.Set;

public class Naming {

	// Some may be missing, but these should be the most important ones
	private static final Set<String> RESERVED_KEYWORDS = Set.of(
		"default", "native", "private", "public", "protected", "class",
		"import", "package", "static", "byte", "char", "short", "int",
		"long", "float", "double", "null", "false", "true", "void"
	);

	/**
	 * This method will return the corrected version of built-in types like "String".
	 * If the given input is no built-in type, the original input is returned.
	 * <p>
	 * Example: string -> String
	 *
	 * @param s the input to check.
	 * @return the corrected version if any.
	 */
	public static String correctBuiltIntTypes(String s) {
		if ("string".equalsIgnoreCase(s)) return "String";
		return s;
	}

	/**
	 * Checks whether the given input matches with a reserved keyword (e.g. "package") and appends
	 * an underscore if so.
	 * <p>
	 * Example: package -> _package
	 *
	 * @param s the input to check.
	 * @return a string matching no reserved keyword.
	 */
	public static String escapeReservedKeywords(String s) {
		if (RESERVED_KEYWORDS.contains(s.toLowerCase())) return "_" + s;
		return s;
	}

	/**
	 * Removes array indicators, underscores and capitalizes the given input with PascalCase in order
	 * to match the most common Java style guidelines.
	 * <p>
	 * Empty inputs are returned as it.
	 *
	 * @param s the input to transform.
	 * @return a string matching the most common Java style guidelines (Google & Oracle).
	 */
	public static String toJavaClass(String s) {
		if (s.isEmpty()) return s;
		s = extractType(s);

		// Remove every underscore and capitalize the following word
		for (int i; (i = s.indexOf('_')) != -1; ) {
			String first = s.substring(0, i);
			String second = s.substring(i + 1);
			s = first + capitalize(second);
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

	/**
	 * Capitalizes the given input.
	 * This will only capitalize the first latter - nothing else.
	 *
	 * @param s the input to capitalize.
	 * @return the capitalized version of the given input.
	 */
	private static String capitalize(String s) {
		if (s.isEmpty()) return s;
		return Character.toUpperCase(s.charAt(0)) + s.substring(1);
	}

}
