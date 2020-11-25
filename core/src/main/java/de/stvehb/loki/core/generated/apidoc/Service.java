package de.stvehb.loki.core.generated.apidoc;

import javax.annotation.processing.Generated;
import lombok.Data;
import lombok.AllArgsConstructor;

@Generated(value = "de.stvehb.loki.generator.java.generate.JavaGenerator")
@Data()
@AllArgsConstructor()
public class Service {

	/**
	 * Documents that this is an apibuilder document, noting the specific version used. 
	 * Internally the version is then used for backwards compatibility when applicable as 
	 * new features are added to apibuilder. Note naming refers to the original name of 
	 * this project, 'apidoc', and is left here to avoid a breaking change for preexisting 
	 * services. 
	 */
	private Apidoc apidoc;

	private String name;

	private Organization organization;

	private Application application;

	/**
	 * Fully qualified namespace for this service 
	 */
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
