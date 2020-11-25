package de.stvehb.loki.core.generated.apidoc;

import javax.annotation.processing.Generated;
import lombok.Data;
import lombok.AllArgsConstructor;

@Generated(value = "de.stvehb.loki.generator.spring.SpringGenerator")
@Data
@AllArgsConstructor
public class Union {

private String name;

private String plural;

private String discriminator;

private String description;

private Deprecation deprecation;

private UnionType[] types;

private Attribute[] attributes;

private String[] interfaces;

}
