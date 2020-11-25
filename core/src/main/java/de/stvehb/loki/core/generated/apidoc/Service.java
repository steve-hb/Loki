package de.stvehb.loki.core.generated.apidoc;

import javax.annotation.processing.Generated;
import lombok.Data;
import lombok.AllArgsConstructor;

@Generated(value = "de.stvehb.loki.generator.spring.SpringGenerator")
@Data
@AllArgsConstructor
public class Service {

private Apidoc apidoc;

private String name;

private Organization organization;

private Application application;

private String namespace;

private String version;

private String base_url;

private String description;

private Info info;

private Header[] headers;

private Import[] imports;

private Enum[] enums;

private Interface[] interfaces;

private Union[] unions;

private Model[] models;

private Resource[] resources;

private Attribute[] attributes;

private Annotation[] annotations;

}
