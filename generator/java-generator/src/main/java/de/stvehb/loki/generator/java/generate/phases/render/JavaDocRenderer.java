package de.stvehb.loki.generator.java.generate.phases.render;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JavaDocRenderer {

	public static String render(String indent, String documentation) {
		if (documentation == null) return null;

		return indent + "/**\n" + // /**
			Arrays.stream(documentation.split("\n"))
				.flatMap(s -> {
					String[] words = s.split(" ");
					List<String> lines = new ArrayList<>();

					String line = "";
					for (String word : words) {
						if (line.length() > 80) {
							lines.add(line);
							line = "";
						}

						line += word;
						line += " ";
					}
					if (!line.isBlank()) lines.add(line);

					return lines.stream();
				})
				.map(s -> indent + " * " + s).collect(Collectors.joining("\n")) // * every line
			+ "\n" + indent + " */"; // **/
	}

}
