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

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.pnc.ppitegrator.pp.model.Release;
import org.jboss.pnc.ppitegrator.pp.rest.ProductPagesService;

import io.quarkus.cache.CacheResult;
import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
@Path("/releases")
public class ReleaseResource implements ReleaseService {
    @Inject
    @RestClient
    ProductPagesService productPagesService;

    @Override
    @Operation(summary = "Get release shortnames", description = "Get release shortnames.")
    @APIResponse(
            responseCode = "200",
            description = "Valid shortnames returned.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = SchemaType.ARRAY)))
    @Counted(name = "getReleaseShortnamesCount", description = "How many release shortnames calls have been performed.")
    @Timed(
            name = "getReleaseShortnamesTimer",
            description = "How long it takes to get the release shortnames.",
            unit = MetricUnits.MILLISECONDS)
    @CacheResult(cacheName = "releases-get-release-shortnames")
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Set<String> getReleaseShortnames() {
        var releases = productPagesService.getAllReleases("shortname");

        return releases.stream()
                .map(Release::getShortname)
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
