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

public record Release(@NotNull @Positive Long id, @JsonProperty("bu_group") @NotNull String buGroup, @JsonProperty("bu_group_shortname") @NotNull String buGroupShortname, @JsonProperty("bu_group_name") @NotNull String buGroupName, @NotNull String bu, @JsonProperty("bu_shortname") @NotNull String buShortname, @JsonProperty("bu_name") @NotNull String buName, @NotNull @Positive Long relgroup, @JsonProperty("relgroup_shortname") @NotNull String relgroupShortname, @JsonProperty("product") @NotNull @Positive Long product, @JsonProperty("product_shortname") @NotNull String productShortname, @JsonProperty("product_name") @NotNull String productName, @NotNull String name, @JsonProperty("name_incl_maint") @NotNull String nameInclMaint, @NotEmpty @Pattern(regexp = "^[a-zA-Z1-9]+[\\w.-]*$") @Size(max = 128) String shortname, @JsonProperty("ga_date") @JsonFormat(pattern = "yyyy-MM-dd") LocalDate gaDate, @JsonProperty("all_ga_tasks") Set<Object> allGaTasks, @NotNull String cpe, @NotNull Boolean canceled, @NotNull Boolean published, @JsonProperty("not_maintained_since") @JsonFormat(pattern = "yyyy-MM-dd") LocalDate notMaintainedSince, @JsonProperty("fullName") @NotNull String fullName, @NotNull Integer phase, @JsonProperty("phase_display") @NotEmpty String phaseDisplay, @JsonProperty("platforms") @NotNull String platforms, @JsonProperty("bz_product") @NotNull String bzProduct, @JsonProperty("bz_version") @NotNull String bzVersion, @JsonProperty("bz_nvr_flag") @NotNull String bzNvrFlag, @JsonProperty("is_project_bool") @NotNull Boolean isProjectBool, @JsonProperty("inherit_comms") @NotNull Boolean inheritComms, @JsonProperty("inherit_docs") @NotNull Boolean inheritDocs, @NotNull String description, @JsonProperty("schedule_mode") @NotNull Integer scheduleMode, @JsonProperty("schedule_mode_display") @NotNull String scheduleModeDisplay, @JsonProperty("schedule_invalid_date_since") @JsonFormat(pattern = "yyyy-MM-dd") LocalDate scheduleInvalidDateSince, @JsonProperty("schedule_validity_display") @NotNull String scheduleValidityDisplay, @JsonProperty("current_maint_phases") @NotNull Set<Object> currentMaintPhases, @JsonProperty("has_endless_schedule") @NotNull Boolean hasEndlessSchedule, @JsonProperty("main_schedule") @NotNull Object mainSchedule, @JsonProperty("last_statuses") @NotNull Set<Object> lastStatuses, @JsonProperty("nearing_milestones") @NotNull Set<Object> nearingMilestones, @JsonProperty("security_xml") @NotNull String securityXml, @JsonProperty("task_ordering") @NotNull String taskOrdering, @JsonProperty("release_ordering") @NotNull Integer releaseOrdering, @JsonProperty("shortname_sort") @NotNull String shortnameSort, @JsonProperty("main_schedule_change_msg") @NotNull String mainScheduleChangeMsg) {
}
