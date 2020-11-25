package de.stvehb.loki.core.generated.apidoc;

import javax.annotation.processing.Generated;
import lombok.Data;
import lombok.AllArgsConstructor;

/**
 * Represents an additional attribute that is attached to one of the objects in apibuilder. 
 * The main use case is to capture additional metadata that doesn't necessarily define 
 * the API but aids in code generation. Examples would be hints for certain code generators 
 * about classes to extend, interfaces to implement, annotations to add, names to assign 
 * to certain methods, etc. The specific attributes will be applicable only in the context 
 * of the specific code generators usings them. 
 */
@Generated(value = "de.stvehb.loki.generator.java.generate.JavaGenerator")
@Data()
@AllArgsConstructor()
public class Attribute {

	private String name;

	private Object value;

	private String description;

	private Deprecation deprecation;

}
