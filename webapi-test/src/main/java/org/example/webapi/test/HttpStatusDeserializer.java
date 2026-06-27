package org.example.webapi.test;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.deser.std.StdDeserializer;

public class HttpStatusDeserializer extends StdDeserializer<HttpStatusCode> {

    public HttpStatusDeserializer() {
        super(HttpStatusCode.class);
    }

    @Override
    public HttpStatusCode deserialize(JsonParser p, DeserializationContext context) throws JacksonException {
        String value = p.getString();
        try {
            return HttpStatus.valueOf(Integer.parseInt(value));
        } catch (IllegalArgumentException _) {
            throw context.weirdStringException(value,
                HttpStatusCode.class,
            "invalid status code");
        }
    }
}
