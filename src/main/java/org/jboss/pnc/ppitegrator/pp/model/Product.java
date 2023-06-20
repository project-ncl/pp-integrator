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
import java.util.Collections;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class Product {
    @NotNull
    @Positive
    private Long id;

    @NotNull
    @Positive
    private Long bu;

    @JsonProperty("bu_shortname")
    @NotEmpty
    private String buShortname;

    @JsonProperty("bu_name")
    @NotEmpty
    private String buName;

    @JsonProperty("product_group_name")
    private String productGroupName;

    @NotEmpty
    private String name;

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z1-9]+[\\w.-]*$")
    @Size(max = 128)
    private String shortname;

    @NotNull
    private String bugzilla;

    @NotNull
    private String platforms;

    @NotNull
    private Set<Release> releases;

    @JsonProperty("is_project_bool")
    @NotNull
    private Boolean isProjectBool;

    @NotNull
    private Boolean canceled;

    @NotNull
    private Boolean published;

    @JsonProperty("not_maintained_since")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate notMaintainedSince;

    @NotNull
    private String description;

    @NotNull
    @Positive
    private Integer phase;

    public @NotNull Long getId() {
        return id;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }

    public @NotNull Long getBu() {
        return bu;
    }

    public void setBu(@NotNull Long bu) {
        this.bu = bu;
    }

    public String getBuShortname() {
        return buShortname;
    }

    public void setBuShortname(String buShortname) {
        this.buShortname = buShortname;
    }

    public String getBuName() {
        return buName;
    }

    public void setBuName(String buName) {
        this.buName = buName;
    }

    public String getProductGroupName() {
        return productGroupName;
    }

    public void setProductGroupName(String productGroupName) {
        this.productGroupName = productGroupName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public @NotNull String getBugzilla() {
        return bugzilla;
    }

    public void setBugzilla(@NotNull String bugzilla) {
        this.bugzilla = bugzilla;
    }

    public @NotNull String getPlatforms() {
        return platforms;
    }

    public void setPlatforms(@NotNull String platforms) {
        this.platforms = platforms;
    }

    public Set<Release> getReleases() {
        return Collections.unmodifiableSet(releases);
    }

    public void setReleases(@NotNull Set<Release> releases) {
        this.releases = releases;
    }

    public @NotNull Boolean getIsProjectBool() {
        return isProjectBool;
    }

    public void setIsProjectBool(@NotNull Boolean isProjectBool) {
        this.isProjectBool = isProjectBool;
    }

    public @NotNull Boolean getCanceled() {
        return canceled;
    }

    public void setCanceled(@NotNull Boolean canceled) {
        this.canceled = canceled;
    }

    public @NotNull Boolean getPublished() {
        return published;
    }

    public void setPublished(@NotNull Boolean published) {
        this.published = published;
    }

    public LocalDate getNotMaintainedSince() {
        return notMaintainedSince;
    }

    public void setNotMaintainedSince(LocalDate notMaintainedSince) {
        this.notMaintainedSince = notMaintainedSince;
    }

    public @NotNull String getDescription() {
        return description;
    }

    public void setDescription(@NotNull String description) {
        this.description = description;
    }

    public @NotNull Integer getPhase() {
        return phase;
    }

    public void setPhase(@NotNull Integer phase) {
        this.phase = phase;
    }
}
