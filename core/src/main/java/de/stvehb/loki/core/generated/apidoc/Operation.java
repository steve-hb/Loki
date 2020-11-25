package de.stvehb.loki.core.generated.apidoc;

import javax.annotation.processing.Generated;
import lombok.Data;
import lombok.AllArgsConstructor;

@Generated(value = "de.stvehb.loki.generator.spring.SpringGenerator")
@Data
@AllArgsConstructor
public class Operation {

private Method method;

private String path;

private String description;

private Deprecation deprecation;

private Body body;

private Parameter[] parameters;

private Response[] responses;

private Attribute[] attributes;

}
