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
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @NotNull
    private String shortname;

    @JsonProperty("ga_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate gaDate;

    @JsonProperty("all_ga_tasks")
    private List<Object> allGaTasks;

    @NotNull
    private String cpe;

    @NotNull
    private Boolean canceled;

    @NotNull
    private Boolean published;

    @JsonProperty("not_maintained_since")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate notMaintainedSince;

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
    private List<Object> currentMaintPhases;

    @JsonProperty("has_endless_schedule")
    @NotNull
    private Boolean hasEndlessSchedule;

    @JsonProperty("main_schedule")
    @NotNull
    Object mainSchedule;

    @JsonProperty("last_statuses")
    @NotNull
    private List<Object> lastStatuses;

    @JsonProperty("nearing_milestones")
    @NotNull
    private List<Object> nearingMilestones;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuGroup() {
        return buGroup;
    }

    public void setBuGroup(String buGroup) {
        this.buGroup = buGroup;
    }

    public String getBuGroupShortname() {
        return buGroupShortname;
    }

    public void setBuGroupShortname(String buGroupShortname) {
        this.buGroupShortname = buGroupShortname;
    }

    public String getBuGroupName() {
        return buGroupName;
    }

    public void setBuGroupName(String buGroupName) {
        this.buGroupName = buGroupName;
    }

    public String getBu() {
        return bu;
    }

    public void setBu(String bu) {
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

    public Long getRelgroup() {
        return relgroup;
    }

    public void setRelgroup(Long relgroup) {
        this.relgroup = relgroup;
    }

    public String getRelgroupShortname() {
        return relgroupShortname;
    }

    public void setRelgroupShortname(String relgroupShortname) {
        this.relgroupShortname = relgroupShortname;
    }

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }

    public String getProductShortname() {
        return productShortname;
    }

    public void setProductShortname(String productShortname) {
        this.productShortname = productShortname;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameInclMaint() {
        return nameInclMaint;
    }

    public void setNameInclMaint(String nameInclMaint) {
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

    public List<Object> getAllGaTasks() {
        return allGaTasks;
    }

    public void setAllGaTasks(List<Object> allGaTasks) {
        this.allGaTasks = allGaTasks;
    }

    public String getCpe() {
        return cpe;
    }

    public void setCpe(String cpe) {
        this.cpe = cpe;
    }

    public Boolean getCanceled() {
        return canceled;
    }

    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public LocalDate getNotMaintainedSince() {
        return notMaintainedSince;
    }

    public void setNotMaintainedSince(LocalDate notMaintainedSince) {
        this.notMaintainedSince = notMaintainedSince;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getPhase() {
        return phase;
    }

    public void setPhase(Integer phase) {
        this.phase = phase;
    }

    public String getPhaseDisplay() {
        return phaseDisplay;
    }

    public void setPhaseDisplay(String phaseDisplay) {
        this.phaseDisplay = phaseDisplay;
    }

    public String getPlatforms() {
        return platforms;
    }

    public void setPlatforms(String platforms) {
        this.platforms = platforms;
    }

    public String getBzProduct() {
        return bzProduct;
    }

    public void setBzProduct(String bzProduct) {
        this.bzProduct = bzProduct;
    }

    public String getBzVersion() {
        return bzVersion;
    }

    public void setBzVersion(String bzVersion) {
        this.bzVersion = bzVersion;
    }

    public String getBzNvrFlag() {
        return bzNvrFlag;
    }

    public void setBzNvrFlag(String bzNvrFlag) {
        this.bzNvrFlag = bzNvrFlag;
    }

    public Boolean getProjectBool() {
        return isProjectBool;
    }

    public void setProjectBool(Boolean projectBool) {
        isProjectBool = projectBool;
    }

    public Boolean getInheritComms() {
        return inheritComms;
    }

    public void setInheritComms(Boolean inheritComms) {
        this.inheritComms = inheritComms;
    }

    public Boolean getInheritDocs() {
        return inheritDocs;
    }

    public void setInheritDocs(Boolean inheritDocs) {
        this.inheritDocs = inheritDocs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getScheduleMode() {
        return scheduleMode;
    }

    public void setScheduleMode(Integer scheduleMode) {
        this.scheduleMode = scheduleMode;
    }

    public String getScheduleModeDisplay() {
        return scheduleModeDisplay;
    }

    public void setScheduleModeDisplay(String scheduleModeDisplay) {
        this.scheduleModeDisplay = scheduleModeDisplay;
    }

    public LocalDate getScheduleInvalidDateSince() {
        return scheduleInvalidDateSince;
    }

    public void setScheduleInvalidDateSince(LocalDate scheduleInvalidDateSince) {
        this.scheduleInvalidDateSince = scheduleInvalidDateSince;
    }

    public String getScheduleValidityDisplay() {
        return scheduleValidityDisplay;
    }

    public void setScheduleValidityDisplay(String scheduleValidityDisplay) {
        this.scheduleValidityDisplay = scheduleValidityDisplay;
    }

    public List<Object> getCurrentMaintPhases() {
        return currentMaintPhases;
    }

    public void setCurrentMaintPhases(List<Object> currentMaintPhases) {
        this.currentMaintPhases = currentMaintPhases;
    }

    public Boolean hasEndlessSchedule() {
        return hasEndlessSchedule;
    }

    public void setHasEndlessSchedule(Boolean hasEndlessSchedule) {
        this.hasEndlessSchedule = hasEndlessSchedule;
    }

    public Object getMainSchedule() {
        return mainSchedule;
    }

    public void setMainSchedule(Object mainSchedule) {
        this.mainSchedule = mainSchedule;
    }

    public List<Object> getLastStatuses() {
        return lastStatuses;
    }

    public void setLastStatuses(List<Object> lastStatuses) {
        this.lastStatuses = lastStatuses;
    }

    public List<Object> getNearingMilestones() {
        return nearingMilestones;
    }

    public void setNearingMilestones(List<Object> nearingMilestones) {
        this.nearingMilestones = nearingMilestones;
    }

    public String getSecurityXml() {
        return securityXml;
    }

    public void setSecurityXml(String securityXml) {
        this.securityXml = securityXml;
    }

    public String getTaskOrdering() {
        return taskOrdering;
    }

    public void setTaskOrdering(String taskOrdering) {
        this.taskOrdering = taskOrdering;
    }

    public Integer getReleaseOrdering() {
        return releaseOrdering;
    }

    public void setReleaseOrdering(Integer releaseOrdering) {
        this.releaseOrdering = releaseOrdering;
    }

    public String getShortnameSort() {
        return shortnameSort;
    }

    public void setShortnameSort(String shortnameSort) {
        this.shortnameSort = shortnameSort;
    }

    public String getMainScheduleChangeMsg() {
        return mainScheduleChangeMsg;
    }

    public void setMainScheduleChangeMsg(String mainScheduleChangeMsg) {
        this.mainScheduleChangeMsg = mainScheduleChangeMsg;
    }
}
