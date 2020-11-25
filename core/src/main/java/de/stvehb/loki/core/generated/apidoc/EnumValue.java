package de.stvehb.loki.core.generated.apidoc;

import javax.annotation.processing.Generated;
import lombok.Data;
import lombok.AllArgsConstructor;

@Generated(value = "de.stvehb.loki.generator.java.generate.JavaGenerator")
@Data()
@AllArgsConstructor()
public class EnumValue {

	private String name;

	private String description;

	private Deprecation deprecation;

	private Attribute[] attributes;

	/**
	 * The actual string representation of this value. If not specified, defaults to 'name' 
	 */
	private String value;

}
