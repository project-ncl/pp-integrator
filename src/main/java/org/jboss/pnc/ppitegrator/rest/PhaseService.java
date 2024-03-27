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

import org.jboss.resteasy.reactive.RestQuery;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/phases")
public interface PhaseService {
    @GET
    @Path("products")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    default String getProductPhase(
            @NotEmpty @Pattern(regexp = "^[a-zA-Z1-9]+[\\w.-]*[^-]$") @Size(max = 128) @RestQuery String shortname) {
        return null;
    }

    @GET
    @Path("releases")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    String getReleasePhase(
            @NotEmpty @Pattern(regexp = "^[a-zA-Z1-9]+[\\w.-]*[^-]$") @Size(max = 128) @RestQuery String shortname);
}
