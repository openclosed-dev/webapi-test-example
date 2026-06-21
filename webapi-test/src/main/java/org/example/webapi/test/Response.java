package org.example.webapi.test;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface Response {

    public record Single(
        @JsonProperty(required = true) WebResource data) implements Response {
    }

    public record Multiple(
        @JsonProperty(required = true) List<WebResource> data) implements Response {
    }

    public record Errors(
        @JsonProperty(required = true) List<Error> errors) implements Response {
    }
}
