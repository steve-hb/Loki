package de.stvehb.loki.core.generated.apidoc;

import javax.annotation.processing.Generated;
import lombok.Data;
import lombok.AllArgsConstructor;

/**
 * An import is used to declare a dependency on another application. This allows you 
 * to reference the models and or enums from that application in your own app. 
 */
@Generated(value = "de.stvehb.loki.generator.java.generate.JavaGenerator")
@Data()
@AllArgsConstructor()
public class Import {

	/**
	 * Full URI to the service.json file of the service we are importing 
	 */
	private String uri;

	/**
	 * the fully qualified namespace that we have imported 
	 */
	private String namespace;

	private Organization organization;

	private Application application;

	/**
	 * The version of the service that we are importing 
	 */
	private String version;

	/**
	 * Enums made available by this import 
	 */
	private String[] enums;

	/**
	 * Interfaces made available by this import 
	 */
	private String[] interfaces;

	/**
	 * Unions made available by this import 
	 */
	private String[] unions;

	/**
	 * Models made available by this import 
	 */
	private String[] models;

	/**
	 * Annotations made available by this import 
	 */
	private Annotation[] annotations;

}
