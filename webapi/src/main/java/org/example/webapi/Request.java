package org.example.webapi;

public interface Request {

    public record One(WebResource data) implements Request {
    }
}
