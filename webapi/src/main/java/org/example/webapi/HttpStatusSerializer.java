package org.example.webapi;

import org.springframework.http.HttpStatusCode;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.std.StdSerializer;

public class HttpStatusSerializer extends StdSerializer<HttpStatusCode> {

    public HttpStatusSerializer() {
        super(HttpStatusCode.class);
    }

    @Override
    public void serialize(HttpStatusCode value, JsonGenerator gen, SerializationContext ctxt) throws JacksonException {
        if (value == null) {
            gen.writeNull();
        } else {
            gen.writeString(String.valueOf(value.value()));
        }
    }
}
