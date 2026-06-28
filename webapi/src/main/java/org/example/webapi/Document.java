package org.example.webapi;

import java.util.List;

public interface Document {

    public record One(WebResource data) implements Document {
    }

    public record Many(List<WebResource> data) implements Document {
    }

    public record Errors(List<Error> errors) implements Document {
    }

    public static Document data(WebResource resource) {
        return new One(resource);
    }

    public static Document data(List<? extends WebResource> resources) {
        return new Many(List.copyOf(resources));
    }

    public static Document errors(Error... error) {
        return errors(List.of(error));
    }

    public static Document errors(List<Error> errors) {
        return new Errors(List.copyOf(errors));
    }
}
