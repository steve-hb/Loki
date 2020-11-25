package de.stvehb.loki.parser;

import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import de.stvehb.loki.core.generated.apidoc.Enum;
import de.stvehb.loki.core.generated.apidoc.Model;
import de.stvehb.loki.core.generated.apidoc.Service;
import de.stvehb.loki.core.generated.apidoc.Union;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class LokiParser {

	private static Gson GSON;

	static {
		try {
			GSON = new GsonBuilder()
				.registerTypeAdapter(
					new TypeToken<Model[]>() {}.getType(), new ObjectToArrayAdapter<Model[]>(Model.class, Model.class.getDeclaredField("name"))
				)
				.registerTypeAdapter(
					new TypeToken<Enum[]>() {}.getType(), new ObjectToArrayAdapter<Model[]>(Enum.class, Enum.class.getDeclaredField("name"))
				)
				.registerTypeAdapter(
					new TypeToken<Union[]>() {}.getType(), new ObjectToArrayAdapter<Union[]>(Union.class, Union.class.getDeclaredField("name"))
				)
				.setPrettyPrinting()
				.create();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		loadFromResource("apibuilder.json");
	}

	@SneakyThrows
	@SuppressWarnings("UnstableApiUsage")
	public static Service loadFromResource(String resource) {
		return load(Resources.toString(Resources.getResource(resource), StandardCharsets.UTF_8));
	}

	@SneakyThrows
	public static Service load(Path path) {
		return load(Files.readString(path, StandardCharsets.UTF_8));
	}

	public static Service load(String content) {
		return GSON.fromJson(content, Service.class);
	}

}
