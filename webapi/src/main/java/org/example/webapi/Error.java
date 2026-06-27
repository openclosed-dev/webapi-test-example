package org.example.webapi;

import java.util.Objects;
import java.util.UUID;

import org.springframework.http.HttpStatusCode;

import tools.jackson.databind.annotation.JsonSerialize;

public interface Error {

    String id();

    String code();

    HttpStatusCode status();

    String detail();

    static Error newError(
        String code, HttpStatusCode status, String detail) {
        return new DefaultError(generateId(), code, status, detail);
    }

    public record DefaultError(
        String id,
        String code,
        @JsonSerialize(using = HttpStatusSerializer.class)
        HttpStatusCode status,
        String detail) implements Error {

        public DefaultError {
            Objects.requireNonNull(id);
            Objects.requireNonNull(code);
            Objects.requireNonNull(status);
            Objects.requireNonNull(detail);
        }
    }

    static String generateId() {
        return UUID.randomUUID().toString();
    }
}
