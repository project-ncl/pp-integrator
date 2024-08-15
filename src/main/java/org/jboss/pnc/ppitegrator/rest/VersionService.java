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

import static jakarta.ws.rs.core.MediaType.TEXT_PLAIN;
import static org.eclipse.microprofile.openapi.annotations.enums.SchemaType.STRING;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/version")
@Schema(title = "Version API")

public interface VersionService {
    @Operation(summary = "Display version information", description = "Display version information.")
    @APIResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(mediaType = TEXT_PLAIN, schema = @Schema(type = STRING)))
    @GET
    @PermitAll
    @Produces(TEXT_PLAIN)
    String getVersion();
}
