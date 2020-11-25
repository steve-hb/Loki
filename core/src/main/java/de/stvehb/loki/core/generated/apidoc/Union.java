package de.stvehb.loki.core.generated.apidoc;

import javax.annotation.processing.Generated;
import lombok.Data;
import lombok.AllArgsConstructor;

@Generated(value = "de.stvehb.loki.generator.java.generate.JavaGenerator")
@Data()
@AllArgsConstructor()
public class Union {

	private String name;

	private String plural;

	/**
	 * If a type discriminator is provided, serialization of these union types will always 
	 * contain a field named with the value of the discriminator that will contain the name 
	 * of the type. This provides a simpler (for many use cases) JSON serialization/deserialization 
	 * mechanism. When specified, apibuilder itself will verify that none of the types in 
	 * the union type itself contain a field with the same name as the discriminator 
	 */
	private String discriminator;

	private String description;

	private Deprecation deprecation;

	/**
	 * The names of the types that make up this union type 
	 */
	private UnionType[] types;

	private Attribute[] attributes;

	private String[] interfaces;

}
