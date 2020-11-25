package de.stvehb.loki.generator.java.generate.phases;

import de.stvehb.loki.core.ast.source.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

public class ResourceOutputPhase {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceOutputPhase.class.getSimpleName());

	public static void processModels(Path targetDir, Map<Model, String> modelContents) {
		modelContents.forEach((model, content) -> {
			try {
				Path targetFile = targetDir.resolve(
					Path.of(model.getNamespace().replaceAll("\\.", "/")) // Convert namespace to package
				).resolve(Path.of(model.getName() + ".java"));

				Files.createDirectories(targetFile.getParent());

				Files.writeString(
					targetFile,
					content,
					StandardOpenOption.CREATE
				);
			} catch (IOException ex) {
				LOGGER.error("Error while writing content of model " + model.getName() + " to file system!", ex);
			}
		});
	}

}
