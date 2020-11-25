package de.stvehb.loki.core.generated.apidoc;

import javax.annotation.processing.Generated;
import lombok.Data;
import lombok.AllArgsConstructor;

@Generated(value = "de.stvehb.loki.generator.spring.SpringGenerator")
@Data
@AllArgsConstructor
public class Resource {

private String type;

private String plural;

private String path;

private String description;

private Deprecation deprecation;

private Operation[] operations;

private Attribute[] attributes;

}
