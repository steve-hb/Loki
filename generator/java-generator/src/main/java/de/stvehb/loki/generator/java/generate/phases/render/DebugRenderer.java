package de.stvehb.loki.generator.java.generate.phases.render;

import de.stvehb.loki.core.option.Context;

public class DebugRenderer {

	public static String generateLineDebugs(Context context) {
		String content = "";

		if (context.getDebugOptions().isAnnotateLines() && !context.getDebuggingStore().getLineStore().isEmpty()) {
			content += ModelRenderer.SINGLE_LINE_COMMENT;
			content += String.join("; ", context.getDebuggingStore().getLineStore());
		}

		context.getDebuggingStore().getLineStore().clear(); // Clear debugging store for this line

		return content;
	}

}
