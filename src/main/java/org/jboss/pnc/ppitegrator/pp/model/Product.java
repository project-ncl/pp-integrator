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
package org.jboss.pnc.ppitegrator.pp.model;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record Product(@NotNull @Positive Long id, @NotNull @Positive Long bu, @NotEmpty String name, @NotEmpty @Pattern(regexp = "^[a-zA-Z1-9]+[\\w.-]*$") @Size(max = 128) String shortname, @NotNull String bugzilla, @NotNull String platforms, @NotNull Set<Release> releases, @NotNull Boolean canceled, @NotNull Boolean published, @NotNull String description, @NotNull @Positive Integer phase, @JsonProperty("not_maintained_since") @JsonFormat(pattern = "yyyy-MM-dd") LocalDate notMaintainedSince, @JsonProperty("is_project_bool") @NotNull Boolean isProjectBool, @JsonProperty("product_group_name") String productGroupName, @JsonProperty("bu_name") @NotEmpty String buName, @JsonProperty("bu_shortname") @NotEmpty String buShortname) {
}
