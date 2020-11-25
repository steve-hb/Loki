package de.stvehb.loki.core.generated.apidoc;

import javax.annotation.processing.Generated;
import lombok.Data;
import lombok.AllArgsConstructor;

@Generated(value = "de.stvehb.loki.generator.java.generate.JavaGenerator")
@Data()
@AllArgsConstructor()
public class Parameter {

	private String name;

	private String type;

	private ParameterLocation location;

	private String description;

	private Deprecation deprecation;

	private Boolean required;

	private String _default;

	private Long minimum;

	private Long maximum;

	private String example;

	private Attribute[] attributes;

}
