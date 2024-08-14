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

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;
import static org.eclipse.microprofile.openapi.annotations.enums.SchemaType.STRING;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.pnc.ppitegrator.pp.model.Phase;
import org.jboss.pnc.ppitegrator.pp.rest.ProductPagesService;
import org.jboss.resteasy.reactive.RestQuery;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.WebApplicationException;

@ApplicationScoped
public class PhaseResource implements PhaseService {
    @RestClient
    ProductPagesService productPagesService;

    @Override
    public String getProductPhase(
            @NotEmpty @Pattern(regexp = "^[a-zA-Z1-9]+[\\w.-]*[^-]$") @Size(max = 128) @Parameter(
                    required = true,
                    schema = @Schema(type = STRING)) @RestQuery String shortname) {
        var products = productPagesService.getProductWithFields(shortname, "phase");
        var size = products.size();

        if (size != 1) {
            throw new WebApplicationException("Expected to get exactly one product, but got " + size, NOT_FOUND);
        }

        var product = products.iterator().next();
        var phase = Phase.fromValue(product.phase());

        return phase.getName();
    }

    @Override
    public String getReleasePhase(
            @NotEmpty @Pattern(regexp = "^[a-zA-Z1-9]+[\\w.-]*[^-]$") @Size(max = 128) @Parameter(
                    required = true,
                    schema = @Schema(type = STRING)) @RestQuery String shortname) {
        var releases = productPagesService.getReleaseWithFields(shortname, "phase");
        var size = releases.size();

        if (size != 1) {
            throw new WebApplicationException("Expected to get exactly one release, but got " + size, NOT_FOUND);
        }

        var release = releases.iterator().next();
        var phase = Phase.fromValue(release.phase());

        return phase.getName();
    }
}
