package de.stvehb.loki.generator.maven.generate;

import de.stvehb.loki.core.ast.Project;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;

import java.util.Arrays;

/**
 * Generates the maven pom files needed to compile/build the resulting source code.
 * This generator only supports single-module projects.
 */
public class MavenGenerator {

	public static void process(Project project) {
		Model model = new Model();
		model.setArtifactId(project.getInfo().getName());
		model.setGroupId(project.getInfo().getNamespace());

		Dependency lombok = new Dependency();
		lombok.setGroupId("org.projectlombok");
		lombok.setArtifactId("lombok");

		model.setDependencies(Arrays.asList(
			lombok
		));

		MavenXpp3Writer writer = new MavenXpp3Writer();
		
	}

}
