package de.stvehb.loki.core.generated.apidoc;

import javax.annotation.processing.Generated;
import lombok.Data;
import lombok.AllArgsConstructor;

@Generated(value = "de.stvehb.loki.generator.spring.SpringGenerator")
@Data
@AllArgsConstructor
public class Response {

private ResponseCode code;

private String type;

private Header[] headers;

private String description;

private Deprecation deprecation;

private Attribute[] attributes;

}
