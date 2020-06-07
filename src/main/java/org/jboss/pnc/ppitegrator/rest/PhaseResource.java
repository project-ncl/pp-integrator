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

import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.pnc.ppitegrator.pp.model.Phase;
import org.jboss.pnc.ppitegrator.pp.model.Product;
import org.jboss.pnc.ppitegrator.pp.model.Release;
import org.jboss.pnc.ppitegrator.pp.rest.ProductPagesService;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import io.quarkus.cache.CacheResult;

@ApplicationScoped
@Path("/phases")
@Schema(title = "Phase API.")
public class PhaseResource implements PhaseService {
    @Inject
    @RestClient
    ProductPagesService productPagesService;

    @Override
    @Operation(summary = "Get product phase", description = "Get product phase.")
    @APIResponse(
            responseCode = "200",
            description = "Valid phase returned.",
            content = @Content(mediaType = MediaType.TEXT_PLAIN, schema = @Schema(type = SchemaType.STRING)))
    @APIResponse(
            responseCode = "404",
            description = "Did not find exactly one result.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = ErrorMessage.class)))
    @Counted(name = "getProductPhaseCount", description = "How many product phase calls have been performed.")
    @Timed(
            name = "getProductPhaseTimer",
            description = "How long it takes to get the product phase.",
            unit = MetricUnits.MILLISECONDS)
    @CacheResult(cacheName = "phases-products")
    @GET
    @Path("products")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public String getProductPhase(
            @NotEmpty @Parameter(
                    required = true,
                    schema = @Schema(type = SchemaType.STRING)) @QueryParam String shortname) {
        Set<Product> products = productPagesService.getProductWithFields(shortname, "phase");
        int size = products.size();

        if (size != 1) {
            throw new NotFoundException("Expected to get exactly one product, but got " + size);
        }

        Product product = products.iterator().next();
        Phase phase = Phase.fromValue(product.getPhase());

        return phase.getName();
    }

    @Override
    @Operation(summary = "Get release phase", description = "Get release phase.")
    @APIResponse(
            responseCode = "200",
            description = "Valid phase returned.",
            content = @Content(mediaType = MediaType.TEXT_PLAIN, schema = @Schema(type = SchemaType.STRING)))
    @APIResponse(
            responseCode = "404",
            description = "Did not find exactly one result.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = ErrorMessage.class)))
    @Counted(name = "getReleasePhaseCount", description = "How many release phase calls have been performed.")
    @Timed(
            name = "getReleasePhaseTimer",
            description = "How long it takes to get the release phase.",
            unit = MetricUnits.MILLISECONDS)
    @CacheResult(cacheName = "phases-releases")
    @GET
    @Path("releases")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public Response getReleasePhase(
            @NotEmpty @Parameter(
                    required = true,
                    schema = @Schema(type = SchemaType.STRING)) @QueryParam String shortname) {
        Set<Release> releases = productPagesService.getRelease(shortname);
        int size = releases.size();

        if (size != 1) {
            return Response
                    .status(
                            Response.Status.NOT_FOUND.getStatusCode(),
                            "Expected to get exactly one release, but got " + size)
                    .build();
        }

        Release release = releases.iterator().next();
        Phase phase = Phase.fromValue(release.getPhase());

        return Response.ok(phase.getName()).build();
    }
}
