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

public class Release {
    @NotNull
    @Positive
    private Long id;

    @JsonProperty("bu_group")
    @NotNull
    private String buGroup;

    @JsonProperty("bu_group_shortname")
    @NotNull
    private String buGroupShortname;

    @JsonProperty("bu_group_name")
    @NotNull
    private String buGroupName;

    @NotNull
    private String bu;

    @JsonProperty("bu_shortname")
    @NotNull
    private String buShortname;

    @JsonProperty("bu_name")
    @NotNull
    private String buName;

    @NotNull
    @Positive
    private Long relgroup;

    @JsonProperty("relgroup_shortname")
    @NotNull
    private String relgroupShortname;

    @JsonProperty("product")
    @NotNull
    @Positive
    private Long product;

    @JsonProperty("product_shortname")
    @NotNull
    private String productShortname;

    @JsonProperty("product_name")
    @NotNull
    private String productName;

    @NotNull
    private String name;

    @JsonProperty("name_incl_maint")
    @NotNull
    private String nameInclMaint;

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z1-9]+[\\w.-]*$")
    @Size(max = 128)
    private String shortname;

    @JsonProperty("ga_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate gaDate;

    @JsonProperty("all_ga_tasks")
    private Set<Object> allGaTasks;

    @NotNull
    private String cpe;

    @NotNull
    private Boolean canceled;

    @NotNull
    private Boolean published;

    @JsonProperty("not_maintained_since")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate notMaintainedSince;

    @JsonProperty("fullName")
    @NotNull
    private String fullName;

    @NotNull
    private Integer phase;

    @JsonProperty("phase_display")
    @NotEmpty
    private String phaseDisplay;

    @JsonProperty("platforms")
    @NotNull
    private String platforms;

    @JsonProperty("bz_product")
    @NotNull
    private String bzProduct;

    @JsonProperty("bz_version")
    @NotNull
    private String bzVersion;

    @JsonProperty("bz_nvr_flag")
    @NotNull
    private String bzNvrFlag;

    @JsonProperty("is_project_bool")
    @NotNull
    private Boolean isProjectBool;

    @JsonProperty("inherit_comms")
    @NotNull
    private Boolean inheritComms;

    @JsonProperty("inherit_docs")
    @NotNull
    private Boolean inheritDocs;

    @NotNull
    private String description;

    @JsonProperty("schedule_mode")
    @NotNull
    private Integer scheduleMode;

    @JsonProperty("schedule_mode_display")
    @NotNull
    private String scheduleModeDisplay;

    @JsonProperty("schedule_invalid_date_since")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate scheduleInvalidDateSince;

    @JsonProperty("schedule_validity_display")
    @NotNull
    private String scheduleValidityDisplay;

    @JsonProperty("current_maint_phases")
    @NotNull
    private Set<Object> currentMaintPhases;

    @JsonProperty("has_endless_schedule")
    @NotNull
    private Boolean hasEndlessSchedule;

    @JsonProperty("main_schedule")
    @NotNull
    private Object mainSchedule;

    @JsonProperty("last_statuses")
    @NotNull
    private Set<Object> lastStatuses;

    @JsonProperty("nearing_milestones")
    @NotNull
    private Set<Object> nearingMilestones;

    @JsonProperty("security_xml")
    @NotNull
    private String securityXml;

    @JsonProperty("task_ordering")
    @NotNull
    private String taskOrdering;

    @JsonProperty("release_ordering")
    @NotNull
    private Integer releaseOrdering;

    @JsonProperty("shortname_sort")
    @NotNull
    private String shortnameSort;

    @JsonProperty("main_schedule_change_msg")
    @NotNull
    private String mainScheduleChangeMsg;

    public @NotNull Long getId() {
        return id;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }

    public @NotNull String getBuGroup() {
        return buGroup;
    }

    public void setBuGroup(@NotNull String buGroup) {
        this.buGroup = buGroup;
    }

    public @NotNull String getBuGroupShortname() {
        return buGroupShortname;
    }

    public void setBuGroupShortname(@NotNull String buGroupShortname) {
        this.buGroupShortname = buGroupShortname;
    }

    public @NotNull String getBuGroupName() {
        return buGroupName;
    }

    public void setBuGroupName(@NotNull String buGroupName) {
        this.buGroupName = buGroupName;
    }

    public @NotNull String getBu() {
        return bu;
    }

    public void setBu(@NotNull String bu) {
        this.bu = bu;
    }

    public @NotNull String getBuShortname() {
        return buShortname;
    }

    public void setBuShortname(@NotNull String buShortname) {
        this.buShortname = buShortname;
    }

    public @NotNull String getBuName() {
        return buName;
    }

    public void setBuName(@NotNull String buName) {
        this.buName = buName;
    }

    public @NotNull Long getRelgroup() {
        return relgroup;
    }

    public void setRelgroup(@NotNull Long relgroup) {
        this.relgroup = relgroup;
    }

    public @NotNull String getRelgroupShortname() {
        return relgroupShortname;
    }

    public void setRelgroupShortname(@NotNull String relgroupShortname) {
        this.relgroupShortname = relgroupShortname;
    }

    public @NotNull Long getProduct() {
        return product;
    }

    public void setProduct(@NotNull Long product) {
        this.product = product;
    }

    public @NotNull String getProductShortname() {
        return productShortname;
    }

    public void setProductShortname(@NotNull String productShortname) {
        this.productShortname = productShortname;
    }

    public @NotNull String getProductName() {
        return productName;
    }

    public void setProductName(@NotNull String productName) {
        this.productName = productName;
    }

    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public @NotNull String getNameInclMaint() {
        return nameInclMaint;
    }

    public void setNameInclMaint(@NotNull String nameInclMaint) {
        this.nameInclMaint = nameInclMaint;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public LocalDate getGaDate() {
        return gaDate;
    }

    public void setGaDate(LocalDate gaDate) {
        this.gaDate = gaDate;
    }

    public Set<Object> getAllGaTasks() {
        return Collections.unmodifiableSet(allGaTasks);
    }

    public void setAllGaTasks(Set<Object> allGaTasks) {
        this.allGaTasks = allGaTasks;
    }

    public @NotNull String getCpe() {
        return cpe;
    }

    public void setCpe(@NotNull String cpe) {
        this.cpe = cpe;
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

    public @NotNull String getFullName() {
        return fullName;
    }

    public void setFullName(@NotNull String fullName) {
        this.fullName = fullName;
    }

    public @NotNull Integer getPhase() {
        return phase;
    }

    public void setPhase(@NotNull Integer phase) {
        this.phase = phase;
    }

    public String getPhaseDisplay() {
        return phaseDisplay;
    }

    public void setPhaseDisplay(String phaseDisplay) {
        this.phaseDisplay = phaseDisplay;
    }

    public @NotNull String getPlatforms() {
        return platforms;
    }

    public void setPlatforms(@NotNull String platforms) {
        this.platforms = platforms;
    }

    public @NotNull String getBzProduct() {
        return bzProduct;
    }

    public void setBzProduct(@NotNull String bzProduct) {
        this.bzProduct = bzProduct;
    }

    public @NotNull String getBzVersion() {
        return bzVersion;
    }

    public void setBzVersion(@NotNull String bzVersion) {
        this.bzVersion = bzVersion;
    }

    public @NotNull String getBzNvrFlag() {
        return bzNvrFlag;
    }

    public void setBzNvrFlag(@NotNull String bzNvrFlag) {
        this.bzNvrFlag = bzNvrFlag;
    }

    public Boolean getProjectBool() {
        return isProjectBool;
    }

    public void setProjectBool(Boolean projectBool) {
        isProjectBool = projectBool;
    }

    public @NotNull Boolean getInheritComms() {
        return inheritComms;
    }

    public void setInheritComms(@NotNull Boolean inheritComms) {
        this.inheritComms = inheritComms;
    }

    public @NotNull Boolean getInheritDocs() {
        return inheritDocs;
    }

    public void setInheritDocs(@NotNull Boolean inheritDocs) {
        this.inheritDocs = inheritDocs;
    }

    public @NotNull String getDescription() {
        return description;
    }

    public void setDescription(@NotNull String description) {
        this.description = description;
    }

    public @NotNull Integer getScheduleMode() {
        return scheduleMode;
    }

    public void setScheduleMode(@NotNull Integer scheduleMode) {
        this.scheduleMode = scheduleMode;
    }

    public @NotNull String getScheduleModeDisplay() {
        return scheduleModeDisplay;
    }

    public void setScheduleModeDisplay(@NotNull String scheduleModeDisplay) {
        this.scheduleModeDisplay = scheduleModeDisplay;
    }

    public LocalDate getScheduleInvalidDateSince() {
        return scheduleInvalidDateSince;
    }

    public void setScheduleInvalidDateSince(LocalDate scheduleInvalidDateSince) {
        this.scheduleInvalidDateSince = scheduleInvalidDateSince;
    }

    public @NotNull String getScheduleValidityDisplay() {
        return scheduleValidityDisplay;
    }

    public void setScheduleValidityDisplay(@NotNull String scheduleValidityDisplay) {
        this.scheduleValidityDisplay = scheduleValidityDisplay;
    }

    public Set<Object> getCurrentMaintPhases() {
        return Collections.unmodifiableSet(currentMaintPhases);
    }

    public void setCurrentMaintPhases(@NotNull Set<Object> currentMaintPhases) {
        this.currentMaintPhases = currentMaintPhases;
    }

    public @NotNull Boolean getHasEndlessSchedule() {
        return hasEndlessSchedule;
    }

    public void setHasEndlessSchedule(@NotNull Boolean hasEndlessSchedule) {
        this.hasEndlessSchedule = hasEndlessSchedule;
    }

    public @NotNull Object getMainSchedule() {
        return mainSchedule;
    }

    public void setMainSchedule(@NotNull Object mainSchedule) {
        this.mainSchedule = mainSchedule;
    }

    public Set<Object> getLastStatuses() {
        return Collections.unmodifiableSet(lastStatuses);
    }

    public void setLastStatuses(@NotNull Set<Object> lastStatuses) {
        this.lastStatuses = lastStatuses;
    }

    public Set<Object> getNearingMilestones() {
        return Collections.unmodifiableSet(nearingMilestones);
    }

    public void setNearingMilestones(@NotNull Set<Object> nearingMilestones) {
        this.nearingMilestones = nearingMilestones;
    }

    public @NotNull String getSecurityXml() {
        return securityXml;
    }

    public void setSecurityXml(@NotNull String securityXml) {
        this.securityXml = securityXml;
    }

    public @NotNull String getTaskOrdering() {
        return taskOrdering;
    }

    public void setTaskOrdering(@NotNull String taskOrdering) {
        this.taskOrdering = taskOrdering;
    }

    public @NotNull Integer getReleaseOrdering() {
        return releaseOrdering;
    }

    public void setReleaseOrdering(@NotNull Integer releaseOrdering) {
        this.releaseOrdering = releaseOrdering;
    }

    public @NotNull String getShortnameSort() {
        return shortnameSort;
    }

    public void setShortnameSort(@NotNull String shortnameSort) {
        this.shortnameSort = shortnameSort;
    }

    public @NotNull String getMainScheduleChangeMsg() {
        return mainScheduleChangeMsg;
    }

    public void setMainScheduleChangeMsg(@NotNull String mainScheduleChangeMsg) {
        this.mainScheduleChangeMsg = mainScheduleChangeMsg;
    }
}
