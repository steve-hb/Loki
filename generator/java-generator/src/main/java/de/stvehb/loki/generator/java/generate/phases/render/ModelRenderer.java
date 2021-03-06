package de.stvehb.loki.generator.java.generate.phases.render;

import de.stvehb.loki.core.ast.Project;
import de.stvehb.loki.core.ast.source.Annotation;
import de.stvehb.loki.core.ast.source.Enum;
import de.stvehb.loki.core.ast.source.Field;
import de.stvehb.loki.core.ast.source.Model;
import de.stvehb.loki.core.option.Context;
import de.stvehb.loki.core.util.Null;

public class ModelRenderer {

	/**
	 * End of statement (semicolon).
	 */
	static final String EOS = ";";
	static final String EOL = EOS + "\n";
	static final String LINE_BREAK = "\n";
	static final String INDENTATION = "	"; //TODO: Tabs vs X spaces? Maybe replace Tabs with X spaces in post-processing?
	static final String SINGLE_LINE_COMMENT = "// ";

	@SuppressWarnings("StringConcatenationInLoop") // Java 8+ will compile this to optimized bytecode
	public static String render(Context context, Project project, Model model) {
		//TODO: Update to Java 15 and use extended string literals
		String content;

		content = PackageRenderer.render(project);
		content += LINE_BREAK;

		content += ImportRenderer.render(context, model);
		content += LINE_BREAK;

		String javaDocs = JavaDocRenderer.render("", model.getDocumentation());
		if (javaDocs != null) {
			content += javaDocs;
			content += LINE_BREAK;
		}

		for (Annotation annotation : model.getAnnotations()) content += AnnotationRenderer.render(annotation) + LINE_BREAK;

		//TODO: Inheritance
		content += "public " + (model instanceof Enum ? "enum" : "class") + " " + model.getName() + " {";
		context.getDebuggingStore().getLineStore().add("Start of model: " + model.getName());
		content += DebugRenderer.generateLineDebugs(context);
		content += LINE_BREAK; // Line break of {
		content += LINE_BREAK; // Empty line between class definition and fields

		for (Field field : model.getFields()) {
			javaDocs = JavaDocRenderer.render(INDENTATION, field.getDocumentation());
			if (javaDocs != null) {
				content += javaDocs;
				content += LINE_BREAK;
			}

			for (Annotation annotation : Null.orEmpty(field.getAnnotations())) content += INDENTATION + AnnotationRenderer.render(annotation) + LINE_BREAK;
			content += INDENTATION + FieldRenderer.render(context, field) + LINE_BREAK; // Render field
			content += LINE_BREAK;
		}

		content += "}";
		context.getDebuggingStore().getLineStore().add("End of model: " + model.getName());
		content += DebugRenderer.generateLineDebugs(context);
		content += LINE_BREAK;

		return content;
	}

}
