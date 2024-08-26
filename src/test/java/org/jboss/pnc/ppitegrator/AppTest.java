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
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.MediaType.TEXT_PLAIN;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.matchesRegex;
import static org.jboss.pnc.ppitegrator.rest.VersionResource.UNKNOWN_VERSION;
import static org.jboss.pnc.ppitegrator.rest.VersionResource.VERSION_PATTERN;
import static org.jboss.resteasy.reactive.RestResponse.StatusCode.BAD_REQUEST;
import static org.jboss.resteasy.reactive.RestResponse.StatusCode.NOT_FOUND;
import static org.jboss.resteasy.reactive.RestResponse.StatusCode.OK;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.pnc.ppitegrator.pp.model.Phase;
import org.jboss.pnc.ppitegrator.rest.ErrorMessage;
import org.junit.jupiter.api.Test;

import io.quarkus.logging.Log;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class AppTest {
    @ConfigProperty(name = "test.product_shortname")
    String productShortname;

    @ConfigProperty(name = "test.release_shortname")
    String releaseShortname;

    private static final String MISSING_SHORTNAME = "XXX";

    private static final String INVALID_SHORTNAME = "000";

    private static void verifyPhase(String name) {
        assertNotNull(name, "Phase name cannot be null");

        var phase = Phase.fromName(name);

        Log.infof("Got phase: %s", phase);
    }

    private static void verifyNotFound(ErrorMessage errorMessage) {
        assertThat(errorMessage.getCode(), is(NOT_FOUND));
        assertThat(errorMessage.getMessage(), containsString("Expected to get exactly one "));
        assertThat(errorMessage.getStackTrace(), is(not(empty())));
    }

    private static void verifyBadRequest(String errorMessage) {
        Log.infof("Error message: %s", errorMessage);
        assertThat(errorMessage, containsString("must match"));
    }

    @Test
    void testPhaseProductEndpoint() {
        var name = given().log()
                .all()
                .accept(TEXT_PLAIN)
                .when()
                .queryParam("shortname", productShortname)
                .get("/api/phases/products")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(OK)
                .extract()
                .asString();

        verifyPhase(name);
    }

    @Test
    void testMissingPhaseProductEndpoint() {
        var errorMessage = given().log()
                .all()
                .accept(TEXT_PLAIN)
                .when()
                .queryParam("shortname", MISSING_SHORTNAME)
                .get("/api/phases/products")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(NOT_FOUND)
                .extract()
                .as(ErrorMessage.class);

        verifyNotFound(errorMessage);
    }

    @Test
    void testInvalidPhaseProductEndpoint() {
        var errorMessage = given().log()
                .all()
                .accept(TEXT_PLAIN)
                .when()
                .queryParam("shortname", INVALID_SHORTNAME)
                .get("/api/phases/products")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(BAD_REQUEST)
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
                .accept(TEXT_PLAIN)
                .when()
                .queryParam("shortname", releaseShortname)
                .get("/api/phases/releases")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(OK)
                .extract()
                .asString();

        verifyPhase(name);
    }

    @Test
    void testMissingPhaseReleaseEndpoint() {
        var errorMessage = given().log()
                .all()
                .accept(TEXT_PLAIN)
                .when()
                .queryParam("shortname", MISSING_SHORTNAME)
                .get("/api/phases/releases")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(NOT_FOUND)
                .extract()
                .as(ErrorMessage.class);

        verifyNotFound(errorMessage);
    }

    @Test
    void testInvalidPhaseReleaseEndpoint() {
        var errorMessage = given().log()
                .all()
                .accept(TEXT_PLAIN)
                .when()
                .queryParam("shortname", INVALID_SHORTNAME)
                .get("/api/phases/releases")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(BAD_REQUEST)
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
                .accept(APPLICATION_JSON)
                .when()
                .get("/api/products")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(OK)
                .extract()
                .as(String[].class);
        var products = Set.of(prods);

        Log.infof("Number of products: %s", products.size());

        assertThat(products, is(not(empty())));
    }

    @Test
    void testReleasesEndpoint() {
        var rels = given().log()
                .all()
                .accept(APPLICATION_JSON)
                .when()
                .get("/api/releases")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(OK)
                .extract()
                .as(String[].class);
        var releases = Set.of(rels);

        Log.infof("Number of releases: %s", releases.size());

        assertThat(releases, is(not(empty())));
    }

    @Test
    void testVersionRegex() {
        assertThat("1.0.0-SNAPSHOT 2024-08-26T11:36:27-04:00 a2bbb6a", matchesRegex(VERSION_PATTERN));
        assertThat("1.0.0-SNAPSHOT 2024-08-26T15:29:09Z f6dfbe7", matchesRegex(VERSION_PATTERN));
        assertThat(UNKNOWN_VERSION, matchesRegex(VERSION_PATTERN));
    }

    @Test
    void testVersionEndpoint() {
        var version = given().log()
                .all()
                .accept(TEXT_PLAIN)
                .when()
                .get("/api/version")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(OK)
                .extract()
                .asString();

        assertThat(version, matchesRegex(VERSION_PATTERN));
    }
}
