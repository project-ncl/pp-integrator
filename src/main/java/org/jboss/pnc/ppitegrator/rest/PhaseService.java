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
package org.jboss.pnc.ppitegrator.rest;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.MediaType.TEXT_PLAIN;
import static org.eclipse.microprofile.metrics.MetricUnits.MILLISECONDS;
import static org.eclipse.microprofile.openapi.annotations.enums.SchemaType.OBJECT;
import static org.eclipse.microprofile.openapi.annotations.enums.SchemaType.STRING;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.resteasy.reactive.RestQuery;

import io.quarkus.cache.CacheResult;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/phases")
@Schema(title = "Phase API")
public interface PhaseService {
    @Operation(summary = "Get product phase", description = "Get product phase.")
    @APIResponse(
            responseCode = "200",
            description = "Valid phase returned.",
            content = @Content(mediaType = TEXT_PLAIN, schema = @Schema(type = STRING)))
    @APIResponse(
            responseCode = "404",
            description = "Did not find exactly one result.",
            content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = ErrorMessage.class)))
    @Counted(name = "getProductPhaseCount", description = "How many product phase calls have been performed.")
    @Timed(
            name = "getProductPhaseTimer",
            description = "How long it takes to get the product phase.",
            unit = MILLISECONDS)
    @CacheResult(cacheName = "phases-products")
    @GET
    @Path("products/")
    @PermitAll
    @Produces(TEXT_PLAIN)
    default String getProductPhase(
            @NotEmpty @Pattern(regexp = "^[a-zA-Z1-9]+[\\w.-]*[^-]$") @Size(max = 128) @RestQuery String shortname) {
        return "";
    }

    @Operation(summary = "Get release phase", description = "Get release phase.")
    @APIResponse(
            responseCode = "200",
            description = "Valid phase returned.",
            content = @Content(mediaType = TEXT_PLAIN, schema = @Schema(type = STRING)))
    @APIResponse(
            responseCode = "404",
            description = "Did not find exactly one result.",
            content = @Content(
                    mediaType = APPLICATION_JSON,
                    schema = @Schema(type = OBJECT, implementation = ErrorMessage.class)))
    @Counted(name = "getReleasePhaseCount", description = "How many release phase calls have been performed.")
    @Timed(
            name = "getReleasePhaseTimer",
            description = "How long it takes to get the release phase.",
            unit = MILLISECONDS)
    @CacheResult(cacheName = "phases-releases")
    @GET
    @Path("releases/")
    @PermitAll
    @Produces(TEXT_PLAIN)
    String getReleasePhase(
            @NotEmpty @Pattern(regexp = "^[a-zA-Z1-9]+[\\w.-]*[^-]$") @Size(max = 128) @RestQuery String shortname);
}
