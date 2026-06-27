package org.example.webapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Project(
    @JsonProperty(required = true)
    String type,
    String id,
    @JsonProperty(required = true)
    String name) implements WebResource {

    public static final String TYPE = "project";

    public Project(String id, String name) {
        this(TYPE, id, name);
    }
}
