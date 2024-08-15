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

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Properties;
import java.util.regex.Pattern;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VersionResource implements VersionService {
    public static final Pattern VERSION_PATTERN = Pattern.compile(
            "^(\\d+\\.\\d+\\.\\d+(-SNAPSHOT)? \\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}[+-]\\d{2}:\\d{2} [0-9a-f]{7}|unknown)$");

    private static final String GIT_PROPERTIES = "git.properties";

    private static final String GIT_BUILD_VERSION = "git.build.version";

    private static final String GIT_BUILD_TIME = "git.build.time";

    private static final String GIT_COMMIT_ID_ABBREV = "git.commit.id.abbrev";

    private static final String UNKNOWN = "unknown";

    private static String version;

    @Override
    public String getVersion() {
        if (version != null) {
            return version;
        }

        try {
            var resources = VersionResource.class.getClassLoader().getResources(GIT_PROPERTIES);
            var props = new Properties();

            while (resources.hasMoreElements()) {
                var url = resources.nextElement();
                Log.debugf("Loaded GIT information from %s", url);

                try (var in = url.openStream()) {
                    props.load(in);
                    var gitBuildVersion = props.getProperty(GIT_BUILD_VERSION);
                    Objects.requireNonNull(gitBuildVersion);
                    var gitBuildTime = props.getProperty(GIT_BUILD_TIME);
                    Objects.requireNonNull(gitBuildTime);
                    var buildTime = OffsetDateTime.parse(gitBuildTime).toString();
                    var gitCommitIdAbbrev = props.getProperty(GIT_COMMIT_ID_ABBREV);
                    Objects.requireNonNull(gitCommitIdAbbrev);
                    version = String.join(" ", gitBuildVersion, buildTime, gitCommitIdAbbrev).stripLeading();
                }
            }
        } catch (Exception e) {
            Log.errorf("Error reading GIT information from %s", GIT_PROPERTIES, e);
            version = UNKNOWN;
        }

        return version;
    }
}
