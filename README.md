# Loki - A "Cloud Native" Code Generation Tool

The aim of Loki is to provide a CLI which lets you create fully-fetched MicroServices and Functions based on a single
API description.

Originally the Loki specification for services was based on apibuilder.io's service specification.
I extended their spec with some new auto-generation- & scaffolding-techniques and features. <br>
For example: They manually declare all CRUD endpoints, this could be done by the generator itself based on field-parameters.

## Goals
> Note: My focus is currently on Java using Quarkus/Spring

The main objective is to develop MicroServices & Functions, which would normally
cost me up to multiple hours of developing, coding, debugging and testing (integration, unit etc.), in under 5 minutes.
Loki should try to generate as much as possible.

* Model generation
  * Field validation (javax: @Email etc.)
  * Indices
* Endpoint generation
  * CRUD
  * Based on models (user -> username/email)
  * Based on the documentation (apidoc -> operation)
* Service logic generation
  * CRUD (endpoint -> service -> repository)
* Unit
  * Unit test generation (in-mem database)
  * Integration test generation
* Event sourcing support
* (PSQL Schema)
  * generation
  * schema updates
* Dependency management support
  * Quarkus Funqy with GraalVM for high-performance cloud native functions
* Docker support
  * Environment variable
* Imports (apidoc)
* (OpenAPI 3.0 generation)
* (Git integration (e.g. updating repositories via GitHub Bots))
* (Public Cloud Provider support? -> Function deployment)

## Features

* Model generation
* CRUD endpoint generation based on models

### Related sources

* [JHipster](https://en.wikipedia.org/wiki/JHipster) - [Yeoman Generator](https://yeoman.io/learning/) for Java
* [ApiBuilder.io](https://www.apibuilder.io/) - The original spec created by [ApiCollective](https://github.com/apicollective)
* [Laravel Generator](https://github.com/InfyOmLabs/laravel-generator)
* [Spring Roo](https://en.wikipedia.org/wiki/Spring_Roo) - A command line based code generation tool for the Spring universe