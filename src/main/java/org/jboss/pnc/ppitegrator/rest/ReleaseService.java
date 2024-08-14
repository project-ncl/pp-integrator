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
import static org.eclipse.microprofile.metrics.MetricUnits.MILLISECONDS;
import static org.eclipse.microprofile.openapi.annotations.enums.SchemaType.ARRAY;

import java.util.Set;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import io.quarkus.cache.CacheResult;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Schema(title = "Release API")
@Path("/releases")
public interface ReleaseService {
    @Operation(summary = "Get release shortnames", description = "Get release shortnames.")
    @APIResponse(
            responseCode = "200",
            description = "Valid shortnames returned.",
            content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(type = ARRAY)))
    @Counted(name = "getReleaseShortnamesCount", description = "How many release shortnames calls have been performed.")
    @Timed(
            name = "getReleaseShortnamesTimer",
            description = "How long it takes to get the release shortnames.",
            unit = MILLISECONDS)
    @CacheResult(cacheName = "releases-get-release-shortnames")
    @GET
    @PermitAll
    @Produces(APPLICATION_JSON)
    Set<String> getReleaseShortnames();
}
