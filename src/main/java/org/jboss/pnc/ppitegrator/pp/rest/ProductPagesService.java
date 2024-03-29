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
package org.jboss.pnc.ppitegrator.pp.rest;

import java.util.Set;
import java.util.concurrent.CompletionStage;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.pnc.ppitegrator.pp.model.Product;
import org.jboss.pnc.ppitegrator.pp.model.Release;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("v7")
@RegisterRestClient
public interface ProductPagesService {
    @GET
    @Path("products/")
    @Produces(MediaType.APPLICATION_JSON)
    Set<Product> getAllProducts(@QueryParam String fields);

    @GET
    @Path("products/")
    @Produces(MediaType.APPLICATION_JSON)
    CompletionStage<Set<Product>> getAllProductsAsync(@QueryParam String fields);

    @GET
    @Path("releases/")
    @Produces(MediaType.APPLICATION_JSON)
    Set<Release> getAllReleases(@QueryParam String fields);

    @GET
    @Path("releases/")
    @Produces(MediaType.APPLICATION_JSON)
    CompletionStage<Set<Release>> getAllReleasesAsync(@QueryParam String fields);

    @GET
    @Path("products/")
    @Produces(MediaType.APPLICATION_JSON)
    Set<Product> getProduct(
            @NotEmpty @Pattern(regexp = "^[a-zA-Z1-9]+[\\w.-]*[^-]$") @Size(max = 128) @QueryParam String shortname);

    @GET
    @Path("products/")
    @Produces(MediaType.APPLICATION_JSON)
    CompletionStage<Set<Product>> getProductAsync(
            @NotEmpty @Pattern(regexp = "^[a-zA-Z1-9]+[\\w.-]*[^-]$") @Size(max = 128) @QueryParam String shortname);

    @GET
    @Path("products/")
    @Produces(MediaType.APPLICATION_JSON)
    Set<Product> getProductWithFields(
            @NotEmpty @Pattern(regexp = "^[a-zA-Z1-9]+[\\w.-]*[^-]$") @Size(max = 128) @QueryParam String shortname,
            @QueryParam String fields);

    @GET
    @Path("products/")
    @Produces(MediaType.APPLICATION_JSON)
    CompletionStage<Set<Product>> getProductWithFieldsAsync(
            @NotEmpty @Pattern(regexp = "^[a-zA-Z1-9]+[\\w.-]*[^-]$") @Size(max = 128) @QueryParam String shortname,
            @QueryParam String fields);

    @GET
    @Path("releases/")
    @Produces(MediaType.APPLICATION_JSON)
    Set<Release> getRelease(
            @NotEmpty @Pattern(regexp = "^[a-zA-Z1-9]+[\\w.-]*[^-]$") @Size(max = 128) @QueryParam String shortname);

    @GET
    @Path("releases/")
    @Produces(MediaType.APPLICATION_JSON)
    CompletionStage<Set<Release>> getReleaseAsync(
            @NotEmpty @Pattern(regexp = "^[a-zA-Z1-9]+[\\w.-]*[^-]$") @Size(max = 128) @QueryParam String shortname);

    @GET
    @Path("releases/")
    @Produces(MediaType.APPLICATION_JSON)
    Set<Release> getReleaseWithFields(
            @NotEmpty @Pattern(regexp = "^[a-zA-Z1-9]+[\\w.-]*[^-]$") @Size(max = 128) @QueryParam String shortname,
            @QueryParam String fields);

    @GET
    @Path("releases/")
    @Produces(MediaType.APPLICATION_JSON)
    CompletionStage<Set<Release>> getReleaseWithFieldsAsync(
            @NotEmpty @Pattern(regexp = "^[a-zA-Z1-9]+[\\w.-]*[^-]$") @Size(max = 128) @QueryParam String shortname,
            @QueryParam String fields);
}
