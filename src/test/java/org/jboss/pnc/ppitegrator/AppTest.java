/*
 * Copyright (C) 2020 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.pnc.ppitegrator;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.LinkedHashSet;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.pnc.ppitegrator.pp.model.Phase;
import org.jboss.pnc.ppitegrator.rest.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@QuarkusTest
class AppTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppTest.class);

    @ConfigProperty(name = "test.product_shortname")
    String productShortname;

    @ConfigProperty(name = "test.release_shortname")
    String releaseShortname;

    private static final String MISSING_SHORTNAME = "XXX";

    private static final String INVALID_SHORTNAME = "000";

    private static void verifyPhase(String name) {
        assertNotNull(name, "Phase name cannot be null");

        var phase = Phase.fromName(name);

        LOGGER.info("Got phase: {}", phase);
    }

    private static void verifyNotFound(ErrorMessage errorMessage) {
        assertThat(errorMessage.getCode(), is(Response.Status.NOT_FOUND.getStatusCode()));
        assertThat(errorMessage.getMessage(), containsString("Expected to get exactly one "));
        assertThat(errorMessage.getStackTrace(), is(not(empty())));
    }

    private static void verifyBadRequest(String errorMessage) {
        LOGGER.info("Error message: {}", errorMessage);
        assertThat(errorMessage, containsString("must match"));
    }

    @Test
    void testPhaseProductEndpoint() {
        var name = given().log()
                .all()
                .accept(MediaType.TEXT_PLAIN)
                .when()
                .queryParam("shortname", productShortname)
                .get("/api/phases/products")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract()
                .asString();

        verifyPhase(name);
    }

    @Test
    void testMissingPhaseProductEndpoint() {
        var errorMessage = given().log()
                .all()
                .accept(MediaType.TEXT_PLAIN)
                .when()
                .queryParam("shortname", MISSING_SHORTNAME)
                .get("/api/phases/products")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode())
                .extract()
                .as(ErrorMessage.class);

        verifyNotFound(errorMessage);
    }

    @Test
    void testInvalidPhaseProductEndpoint() {
        var errorMessage = given().log()
                .all()
                .accept(MediaType.TEXT_PLAIN)
                .when()
                .queryParam("shortname", INVALID_SHORTNAME)
                .get("/api/phases/products")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
                .header("validation-exception", "true")
                .extract()
                .body()
                .asString();

        verifyBadRequest(errorMessage);
    }

    @Test
    void testPhaseReleaseEndpoint() {
        var name = given().log()
                .all()
                .accept(MediaType.TEXT_PLAIN)
                .when()
                .queryParam("shortname", releaseShortname)
                .get("/api/phases/releases")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract()
                .asString();

        verifyPhase(name);
    }

    @Test
    void testMissingPhaseReleaseEndpoint() {
        var errorMessage = given().log()
                .all()
                .accept(MediaType.TEXT_PLAIN)
                .when()
                .queryParam("shortname", MISSING_SHORTNAME)
                .get("/api/phases/releases")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode())
                .extract()
                .as(ErrorMessage.class);

        verifyNotFound(errorMessage);
    }

    @Test
    void testInvalidPhaseReleaseEndpoint() {
        var errorMessage = given().log()
                .all()
                .accept(MediaType.TEXT_PLAIN)
                .when()
                .queryParam("shortname", INVALID_SHORTNAME)
                .get("/api/phases/releases")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
                .header("validation-exception", "true")
                .extract()
                .body()
                .asString();

        verifyBadRequest(errorMessage);
    }

    @Test
    void testProductsEndpoint() {
        var prods = given().log()
                .all()
                .accept(MediaType.APPLICATION_JSON)
                .when()
                .get("/api/products")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract()
                .as(String[].class);
        var products = new LinkedHashSet<>(Arrays.asList(prods));

        LOGGER.info("Number of products: {}", products.size());

        assertThat(products, is(not(empty())));
    }

    @Test
    void testReleasesEndpoint() {
        var rels = given().log()
                .all()
                .accept(MediaType.APPLICATION_JSON)
                .when()
                .get("/api/releases")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract()
                .as(String[].class);
        var releases = new LinkedHashSet<>(Arrays.asList(rels));

        LOGGER.info("Number of releases: {}", releases.size());

        assertThat(releases, is(not(empty())));
    }
}
