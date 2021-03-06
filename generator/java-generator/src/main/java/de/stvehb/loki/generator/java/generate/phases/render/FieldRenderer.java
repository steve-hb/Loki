package de.stvehb.loki.generator.java.generate.phases.render;

import de.stvehb.loki.core.ast.source.Field;
import de.stvehb.loki.core.option.Context;
import de.stvehb.loki.core.option.JavaGeneratorOptions;

public class FieldRenderer {

	public static String render(Context context, Field field) {
		String content = "private ";
		context.getDebuggingStore().getLineStore().add("Render: Hardcoded accessor -> private");

		boolean useListForArray = JavaGeneratorOptions.useListForArrays(context);

		if (field.getMapValueType() != null) content += "Map<";
		if (field.isArray() && useListForArray) content += "List<";

		content += field.getType().getName();
		context.getDebuggingStore().getLineStore().add("Render: Field type -> " + field.getType().getName());

		if (field.getMapValueType() != null) {
			content +=", ";
			content += field.getMapValueType().getName();
			content += ">";

			context.getDebuggingStore().getLineStore().add("Render: Map value type -> " + field.getMapValueType().getName());
		}

		if (field.isArray()) {
			if (useListForArray) {
				content += ">";
				context.getDebuggingStore().getLineStore().add("Render: Field is array & useListForArrays is active");
			} else {
				content += "[]";
				context.getDebuggingStore().getLineStore().add("Render: Field is array");
			}
		}

		content += " ";
		content += field.getName();
		context.getDebuggingStore().getLineStore().add("Render: Field name -> " + field.getName());

		content += ModelRenderer.EOS;
		content += DebugRenderer.generateLineDebugs(context);

		return content;
	}

}
