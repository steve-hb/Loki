package de.stvehb.loki.core.generated.apidoc;

import javax.annotation.processing.Generated;
import lombok.Data;
import lombok.AllArgsConstructor;

/**
 * Metadata about one of the types that is part of a union type 
 */
@Generated(value = "de.stvehb.loki.generator.java.generate.JavaGenerator")
@Data()
@AllArgsConstructor()
public class UnionType {

	/**
	 * The name of a type (a primitive, model name, or enum name) that makes up this union 
	 * type 
	 */
	private String type;

	private String description;

	private Deprecation deprecation;

	private Attribute[] attributes;

	/**
	 * If true, indicates that this type should be used as the default when deserializing 
	 * union types. This field is only used by union types that require a discriminator 
	 * and sets the default value for that disciminator during deserialization. 
	 */
	private Boolean _default;

	/**
	 * The discriminator value defines the string to use in the discriminator field to identify 
	 * this type. If not specified, the discriminator value will default to the name of 
	 * the type itself. 
	 */
	private String discriminator_value;

}
