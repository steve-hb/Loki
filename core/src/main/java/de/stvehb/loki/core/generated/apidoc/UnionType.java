package de.stvehb.loki.core.generated.apidoc;

import javax.annotation.processing.Generated;
import lombok.Data;
import lombok.AllArgsConstructor;

@Generated(value = "de.stvehb.loki.generator.spring.SpringGenerator")
@Data
@AllArgsConstructor
public class UnionType {

private String type;

private String description;

private Deprecation deprecation;

private Attribute[] attributes;

private boolean _default;

private String discriminator_value;

}
