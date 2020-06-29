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

public enum Phase {
    CONCEPT(100, "Concept"),
    PLANNING(200, "Planning"),
    PLANNING_DEVELOPMENT_TESTING(230, "Planning / Development / Testing"),
    CI_CD(270, "CI / CD"),
    DEVELOPMENT(300, "Development"),
    DEVELOPMENT_TESTING(350, "Development / Testing"),
    TESTING(400, "Testing"),
    LAUNCH(500, "Launch"),
    MAINTENANCE(600, "Maintenance"),
    UNSUPPORTED(1000, "Unsupported");

    private final int value;

    private final String name;

    Phase(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static Phase fromValue(Integer value) {
        if (value == null) {
            throw new NullPointerException("value cannot be null");
        }

        for (var phase : Phase.values()) {
            if (phase.getValue() == value) {
                return phase;
            }
        }

        throw new IllegalArgumentException("No phase with value " + value);
    }

    public static Phase fromName(String name) {
        if (name == null) {
            throw new NullPointerException("name cannot be null");
        }

        for (Phase phase : Phase.values()) {
            if (phase.getName().equals(name)) {
                return phase;
            }
        }

        throw new IllegalArgumentException("No phase with name " + name);
    }
}
