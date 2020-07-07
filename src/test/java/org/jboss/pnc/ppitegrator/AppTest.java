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
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.LinkedHashSet;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.pnc.ppitegrator.pp.model.Phase;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class AppTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppTest.class);

    @ConfigProperty(name = "test.product_shortname")
    String productShortname;

    @ConfigProperty(name = "test.release_shortname")
    String releaseShortname;

    private void verifyPhase(String name) {
        assertNotNull(name);

        var phase = Phase.fromName(name);

        LOGGER.info("Got phase {}", phase);
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
