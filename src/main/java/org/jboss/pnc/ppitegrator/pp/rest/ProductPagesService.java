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

import javax.validation.constraints.NotEmpty;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.pnc.ppitegrator.pp.model.Product;
import org.jboss.pnc.ppitegrator.pp.model.Release;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

@Path("v7")
@RegisterRestClient
public interface ProductPagesService {
    @GET
    @Path("products")
    @Produces(MediaType.APPLICATION_JSON)
    Set<Product> getAllProducts(@QueryParam String fields);

    @GET
    @Path("products")
    @Produces(MediaType.APPLICATION_JSON)
    CompletionStage<Set<Product>> getAllProductsAsync(@QueryParam String fields);

    @GET
    @Path("releases")
    @Produces(MediaType.APPLICATION_JSON)
    Set<Release> getAllReleases(@QueryParam String fields);

    @GET
    @Path("releases")
    @Produces(MediaType.APPLICATION_JSON)
    CompletionStage<Set<Release>> getAllReleasesAsync(@QueryParam String fields);

    @GET
    @Path("products")
    @Produces(MediaType.APPLICATION_JSON)
    Set<Product> getProduct(@NotEmpty @QueryParam String shortname);

    @GET
    @Path("products")
    @Produces(MediaType.APPLICATION_JSON)
    CompletionStage<Set<Product>> getProductAsync(@NotEmpty @QueryParam String shortname);

    @GET
    @Path("products")
    @Produces(MediaType.APPLICATION_JSON)
    Set<Product> getProductWithFields(@NotEmpty @QueryParam String shortname, @QueryParam String fields);

    @GET
    @Path("products")
    @Produces(MediaType.APPLICATION_JSON)
    CompletionStage<Set<Product>> getProductWithFieldsAsync(
            @NotEmpty @QueryParam String shortname,
            @QueryParam String fields);

    @GET
    @Path("releases")
    @Produces(MediaType.APPLICATION_JSON)
    Set<Release> getRelease(@NotEmpty @QueryParam String shortname);

    @GET
    @Path("releases")
    @Produces(MediaType.APPLICATION_JSON)
    CompletionStage<Set<Release>> getReleaseAsync(@NotEmpty @QueryParam String shortname);
}
