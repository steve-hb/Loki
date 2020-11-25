package de.stvehb.loki.core.generated.apidoc;

import javax.annotation.processing.Generated;
import lombok.Data;
import lombok.AllArgsConstructor;

@Generated(value = "de.stvehb.loki.generator.java.generate.JavaGenerator")
@Data()
@AllArgsConstructor()
public class Header {

	private String name;

	private String type;

	private String description;

	private Deprecation deprecation;

	private Boolean required;

	private String _default;

	private Attribute[] attributes;

}
