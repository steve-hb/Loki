package de.stvehb.loki.core.generated.apidoc;

import javax.annotation.processing.Generated;
import lombok.Data;
import lombok.AllArgsConstructor;

@Generated(value = "de.stvehb.loki.generator.java.generate.JavaGenerator")
@Data()
@AllArgsConstructor()
public class Enum {

	private String name;

	private String plural;

	private String description;

	private Deprecation deprecation;

	private EnumValue[] values;

	private Attribute[] attributes;

}
