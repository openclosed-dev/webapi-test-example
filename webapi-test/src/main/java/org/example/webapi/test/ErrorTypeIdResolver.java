package org.example.webapi.test;

import org.example.webapi.test.Error.DefaultError;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.DatabindContext;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.jsontype.impl.TypeIdResolverBase;

public class ErrorTypeIdResolver extends TypeIdResolverBase {

    private JavaType superType;

    @Override
    public void init(JavaType superType) {
        this.superType = superType;
    }

    @Override
    public JavaType typeFromId(DatabindContext context, String code) {
        var subclass = switch(code) {
            default -> DefaultError.class;
        };
        return context.constructSpecializedType(superType, subclass);
    }

    @Override
    public String idFromValue(DatabindContext context, Object value) throws JacksonException {
        return idFromValueAndType(context, value, value.getClass());
    }

    @Override
    public String idFromValueAndType(DatabindContext context, Object value, Class<?> suggestedType)
            throws JacksonException {
        throw new UnsupportedOperationException("Unimplemented method 'idFromValueAndType'");
    }

    @Override
    public Id getMechanism() {
        return Id.NAME;
    }
}
