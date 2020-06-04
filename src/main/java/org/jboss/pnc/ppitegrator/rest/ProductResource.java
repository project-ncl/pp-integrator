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

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.pnc.ppitegrator.pp.model.Product;
import org.jboss.pnc.ppitegrator.pp.rest.ProductPagesService;

import io.quarkus.cache.CacheResult;

@ApplicationScoped
@Path("/products")
public class ProductResource implements ProductService {
    @Inject
    @RestClient
    ProductPagesService productPagesService;

    @Override
    @Operation(description = "Get product shortnames")
    @APIResponse(responseCode = "200", description = "Valid shortnames returned")
    @Counted(name = "getProductShortnamesCount", description = "How many product shortnames calls have been performed.")
    @Timed(
            name = "getProductShortnamesTimer",
            description = "How long it takes to get the product shortnames.",
            unit = MetricUnits.MILLISECONDS)
    @CacheResult(cacheName = "products-get-product-shortnames")
    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductShortnames() {
        Set<Product> products = productPagesService.getAllProducts("shortname");
        Set<String> shortnames = products.stream()
                .map(Product::getShortname)
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));

        return Response.ok(shortnames).build();
    }
}
