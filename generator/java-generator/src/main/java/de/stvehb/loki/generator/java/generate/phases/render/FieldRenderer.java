package de.stvehb.loki.generator.java.generate.phases.render;

import de.stvehb.loki.core.ast.source.Field;
import de.stvehb.loki.core.option.Context;

public class FieldRenderer {

	public static String render(Context context, Field field) {
		String content = "private ";

		if (field.getMapValueType() != null) content += "Map<";

		content += field.getType().getName();

		if (field.getMapValueType() != null) content += ">";
		if (field.isArray()) content += "[]";

		content += " ";
		content += field.getName();

		content += ModelRenderer.EOS;

		// Add debugging information
		context.getDebuggingStore().getLineStore().add("Render: Hardcoded accessor -> private");
		context.getDebuggingStore().getLineStore().add("Render: Field type -> " + field.getType().getName());
		context.getDebuggingStore().getLineStore().add("Render: Field name -> " + field.getName());
		if (field.getMapValueType() != null)
			context.getDebuggingStore().getLineStore().add("Render: Field is typeof Map (Map value type not null)");
		if (field.isArray())
			context.getDebuggingStore().getLineStore().add("Render: Field is array");
		content += DebugRenderer.generateLineDebugs(context);

		return content;
	}

}
