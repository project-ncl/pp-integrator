# pp-integrator

Service to integrate with Product Pages.

This project uses Quarkus.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```
mvn quarkus:dev
```

## Packaging and running the application

The application can be packaged using `mvn package -DskipTests`.
It produces the `pp-integrator-1.0.0-SNAPSHOT-runner.jar` file in the `./target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `./target/lib` directory.

The application is now runnable using `java -Dorg.jboss.pnc.ppitegrator.pp.rest.ProductPagesService/mp-rest/url=<pp_url> -jar target/pp-integrator-1.0.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `mvn package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `mvn package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/pp-integrator-1.0.0-SNAPSHOT-runner`.

## Running the tests

To run the tests, you must set a few properties.

```
$ mvn clean install -Dorg.jboss.pnc.ppitegrator.pp.rest.ProductPagesService/mp-rest/url=<pp_url> -Dtest.product_shortname=<product_shortname> -Dtest.release_shortname=<release_shortname>
```

The `org.jboss.pnc.ppitegrator.pp.rest.ProductPagesService/mp-rest/url` property is used at runtime and during the tests and should be set to the base URL of the Product Pages server (without the version).

The `test.product_shortname` property should be set to a valid product shortname, e.g., `jbossfoo`.

The `test.release_shortname` property should be set to valid release shortname. e.g., `jbossfoo-1-0.0`.
