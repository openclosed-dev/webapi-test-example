package org.example.webapi;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.DatabindContext;
import tools.jackson.databind.jsontype.impl.TypeIdResolverBase;

public class WebResourceTypeIdResolver extends TypeIdResolverBase {

    @Override
    public String idFromValue(DatabindContext context, Object value) throws JacksonException {
        return idFromValueAndType(context, value, value.getClass());
    }

    @Override
    public String idFromValueAndType(DatabindContext context, Object value, Class<?> suggestedType)
            throws JacksonException {
        return switch (value) {
            case Project _ -> "project";
            // null will be handled in caller site
            default -> null;
        };
    }

    @Override
    public Id getMechanism() {
        return Id.NAME;
    }
}
