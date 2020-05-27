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

import javax.annotation.security.PermitAll;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import io.quarkus.cache.CacheResult;

@Path("/phases")
public interface PhaseService {
    @CacheResult(cacheName = "phases-products")
    @GET
    @Path("products")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    Response getProductPhase(@NotEmpty @QueryParam String shortname);

    @CacheResult(cacheName = "phases-releases")
    @GET
    @Path("releases")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    Response getReleasePhase(@NotEmpty @QueryParam String shortname);
}
