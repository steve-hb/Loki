package de.stvehb.loki.core.generated.apidoc;

import javax.annotation.processing.Generated;
import lombok.Data;
import lombok.AllArgsConstructor;

@Generated(value = "de.stvehb.loki.generator.spring.SpringGenerator")
@Data
@AllArgsConstructor
public class Field {

private String name;

private String type;

private String description;

private Deprecation deprecation;

private String _default;

private boolean required;

private long minimum;

private long maximum;

private String example;

private Attribute[] attributes;

private String[] annotations;

}
