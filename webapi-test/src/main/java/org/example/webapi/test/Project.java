package org.example.webapi.test;

public record Project(String id, String name) implements WebResource {

    public static final String TYPE = "project";
}
