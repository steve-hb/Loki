package de.stvehb.loki.core.generated.apidoc;

import javax.annotation.processing.Generated;
import lombok.Data;
import lombok.AllArgsConstructor;

@Generated(value = "de.stvehb.loki.generator.spring.SpringGenerator")
@Data
@AllArgsConstructor
public class Import {

private String uri;

private String namespace;

private Organization organization;

private Application application;

private String version;

private String[] enums;

private String[] interfaces;

private String[] unions;

private String[] models;

private Annotation[] annotations;

}
