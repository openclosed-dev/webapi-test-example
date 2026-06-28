package org.example.webapi.test;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface Document {

    public record One(
        @JsonProperty(required = true) WebResource data) implements Document {
    }

    public record Many(
        @JsonProperty(required = true) List<WebResource> data) implements Document {
    }

    public record Errors(
        @JsonProperty(required = true) List<Error> errors) implements Document {
    }

    static Document data(WebResource data) {
        return new One(data);
    }

    static Document data(List<WebResource> data) {
        return new Many(data);
    }
}
