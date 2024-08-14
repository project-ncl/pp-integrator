# pp-integrator

PP (Product Pages) Integrator is a RESTful Web Service that integrates
with Product Pages.

It is currently not intended to be a full implementation of the PP API
in Java. It is meant to be used as a microservice for obtaining the
support status (phase) of a product. Therefore, it currently supports
only Products, Releases, and Phases internally, and externally exposes
an endpoint to get the phase of a product or release using,
respectively:

`/api/phases/products?shortname=jbossfoo`

or

`/api/phases/releases?shortname=jbossfoo-1-0.0`

Assuming it has been given a valid shortname, the service will return
one of the phase names listed below in `text/plain` format:

- Concept
- Planning
- Planning / Development / Testing
- CI / CD Development
- Development / Testing
- Testing
- Launch
- Maintenance
- Unsupported

Additionally, the endpoints `/api/products` and `/api/releases` return
sets of strings representing all product shortnames and all release
shortnames, respectively.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```
mvn quarkus:dev
```

## Packaging and running the application

The application can be packaged using `mvn package -DskipTests`. It
produces the `pp-integrator-runner.jar` file in the `./target` directory
which is an _über-jar_.

The application is now runnable using `java
-Dorg.jboss.pnc.ppitegrator.pp.rest.ProductPagesService/mp-rest/url=<pp_url>
-jar target/pp-integrator-runner.jar`.

## Creating a native executable

You can create a native executable using: `mvn package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native
executable build in a container using: `mvn package -Pnative
-Dquarkus.native.container-build=true`.

You can then execute your native executable with:
`./target/pp-integrator-runner`.

## Running the tests

To run the tests, you must set a few properties.

```
$ mvn clean install -Dpp.url=https://<pp_host>/pp/api -Dtest.product_shortname=<product_shortname> -Dtest.release_shortname=<release_shortname>
```

The `org.jboss.pnc.ppitegrator.pp.rest.ProductPagesService/mp-rest/url`
property is used at runtime and during the tests and should be set to
the base URL of the Product Pages server (without the version).

The `test.product_shortname` property should be set to a valid product
shortname, e.g., `jbossfoo`.

The `test.release_shortname` property should be set to valid release
shortname. e.g., `jbossfoo-<major_version>-<minor_version>.<micro_version>`.
