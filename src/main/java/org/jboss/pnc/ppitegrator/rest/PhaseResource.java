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
import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.pnc.ppitegrator.pp.model.Phase;
import org.jboss.pnc.ppitegrator.pp.model.Product;
import org.jboss.pnc.ppitegrator.pp.model.Release;
import org.jboss.pnc.ppitegrator.pp.rest.ProductPagesService;
import org.jboss.resteasy.annotations.cache.Cache;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

@Path("/phases")
@ApplicationScoped
public class PhaseResource implements PhaseService {
    @Inject
    @RestClient
    ProductPagesService productPagesService;

    @GET
    @Path("products")
    @Produces(MediaType.TEXT_PLAIN)
    @PermitAll
    @Cache
    public Response getProductPhase(@NotEmpty @QueryParam String shortname) {
        Set<Product> products = productPagesService.getProductWithFields(shortname, "phase");
        int size = products.size();

        if (size != 1) {
            throw new BadRequestException("Expected size 1, got size " + size);
        }

        Product product = products.iterator().next();

        Phase phase = Phase.fromValue(product.getPhase());

        return Response.ok(phase.getName()).build();
    }

    @GET
    @Path("releases")
    @Produces(MediaType.TEXT_PLAIN)
    @PermitAll
    @Cache
    public Response getReleasePhase(@NotEmpty @QueryParam String shortname) {
        Set<Release> releases = productPagesService.getRelease(shortname);
        int size = releases.size();

        if (size != 1) {
            throw new BadRequestException("Expected size 1, got size " + size);
        }

        Release release = releases.iterator().next();

        Phase phase = Phase.fromValue(release.getPhase());

        return Response.ok(phase.getName()).build();
    }
}
