package org.example.webapi;

import java.util.List;

public interface Response {

    public record One(WebResource data) implements Response {
    }

    public record Many(List<WebResource> data) implements Response {
    }

    public record Errors(List<Error> errors) implements Response {
    }

    public static Response data(WebResource resource) {
        return new One(resource);
    }

    public static Response data(List<? extends WebResource> resources) {
        return new Many(List.copyOf(resources));
    }

    public static Response error(Error error) {
        return new Errors(List.of(error));
    }

    public static Response errors(List<Error> errors) {
        return new Errors(List.copyOf(errors));
    }
}
