package de.stvehb.loki.parser;

import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.stvehb.loki.core.ast.Project;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class LokiParser {

	private static Gson GSON;

	static {
		GSON = new GsonBuilder()
			.setPrettyPrinting()
			.create();
	}

	@SneakyThrows
	@SuppressWarnings("UnstableApiUsage")
	public static Project loadFromResource(String resource) {
		return load(Resources.toString(Resources.getResource(resource), StandardCharsets.UTF_8));
	}

	@SneakyThrows
	public static Project load(Path path) {
		return load(Files.readString(path, StandardCharsets.UTF_8));
	}

	public static Project load(String content) {
		return GSON.fromJson(content, Project.class);
	}

}
