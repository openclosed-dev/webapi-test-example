package org.example.webapi.test;

import java.util.Objects;

import org.springframework.http.HttpStatusCode;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonTypeIdResolver;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "code",
    visible = true)
@JsonTypeIdResolver(ErrorTypeIdResolver.class)
public interface Error {

    String id();

    String code();

    HttpStatusCode status();

    String detail();

    public record DefaultError(
        String id,
        String code,
        @JsonDeserialize(using = HttpStatusDeserializer.class)
        HttpStatusCode status,
        String detail) implements Error {

        public DefaultError {
            Objects.requireNonNull(id);
            Objects.requireNonNull(code);
            Objects.requireNonNull(status);
            Objects.requireNonNull(detail);
        }
    }
}
