package de.stvehb.loki.core.generated.apidoc;

import javax.annotation.processing.Generated;
import lombok.Data;
import lombok.AllArgsConstructor;

/**
 * Used to indicate an API concern for a field that is specific to the field's usage 
 * but not necessarily its data type. For example, you might use annotations to mark 
 * that certain fields contain PII or PCI data and thus should not be stored once processing 
 * is complete. Annotations communicate meaning to consumers of an API and may also 
 * be used within an implementation or tooling; for example, using static analysis tools 
 * to detect logging of sensitive data. 
 */
@Generated(value = "de.stvehb.loki.generator.java.generate.JavaGenerator")
@Data()
@AllArgsConstructor()
public class Annotation {

	private String name;

	private String description;

	private Deprecation deprecation;

}
