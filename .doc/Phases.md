# Loki Phases

This document describes all the stages involved in (parsing), building the AST, transforming the AST and rendering
the AST.
Keep in mind that the main focus of Loki is on generating Java and Spring projects, so this documentation will
include phases and descriptions available in the Java generators.

## 1. AST Generation

This phase generates or loads the AST from an input source.

## 2. AST Transformation

Here we do all the heavy lifting and logic around creating new models using the provided context, resolving types etc.
In an ideal scenario, this phase should never contain any language specific code since every language uses some
kind of imports for example.
Some may not support annotations, but this is the responsibility of the user to not use them.

## 2.1 Type Resolving

The AST at this point includes redundant type instances. This phase is responsible for resolving the types used by the
fields in the project.
If a type can't be found, an error will be thrown.

## 2.2 Generation Tagging

We should tag the generated files as generated including the generator name and maybe even the version.
In Java this phase is going to add an annotation to all models and enums provided by the project.

## 2.3 Lombokify

This phase is extremely Java specific: It will add some nice Lombok annotations to all models and enums which
will generate getters, setters and constructors.

## 2.4 Linting

This phase is responsible for escaping illegal field or class names and compliance with general
code style guidelines (e.g. field and class naming) of the used language.

## 2.5 Import Resolving

This phase resolves all imports caused by types used in a model.

* Model
    * Annotations
        * **Type**
* Field
    * Annotations
        * **Type**
    * Type (in case of a Map, this type is the key)
    * Map-Value **Type**

## 3. Rendering

At this point all the magic and logic is done, and we now want to render the AST as valid source code.

This is how the Java generator renders the AST:

* Package
* Imports
* Model documentation
* Model annotations
* Model
    * Fields
        * Field documentation
        * Field-Annotations
        * Field

## 4. Output

The generated output now gets written to either the console or text files.
