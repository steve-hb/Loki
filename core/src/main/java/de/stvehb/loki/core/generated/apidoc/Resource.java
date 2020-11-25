package de.stvehb.loki.core.generated.apidoc;

import javax.annotation.processing.Generated;
import lombok.Data;
import lombok.AllArgsConstructor;

@Generated(value = "de.stvehb.loki.generator.java.generate.JavaGenerator")
@Data()
@AllArgsConstructor()
public class Resource {

	/**
	 * The type of this resource will map to a defined model, enum, or union type 
	 */
	private String type;

	private String plural;

	/**
	 * The path to this specific resource. This was added in 2016 to help us differentiate 
	 * between the resource path and the operation path which can be helpful when, for example, 
	 * generating method names for operations. This field is optional as some of our input 
	 * formats (e.g. swagger) do not explicitly differentiate resoure paths. 
	 */
	private String path;

	private String description;

	private Deprecation deprecation;

	private Operation[] operations;

	private Attribute[] attributes;

}
