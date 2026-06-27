package org.example.webapi.test;

public record Project(String type, String id, String name) implements WebResource {

    public static final String TYPE = "project";

    public Project {
        if (!TYPE.equals(type)) {
            throw new IllegalArgumentException(type);
        }
    }

    public Project(String id, String name) {
        this(TYPE, id, name);
    }

    public Project(String name) {
        this(null, name);
    }
}
