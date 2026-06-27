package org.example.webapi.test;

public interface Request {

    public record One(WebResource data) implements Request {
    }

    public static Request data(WebResource resource) {
        return new One(resource);
    }
}
