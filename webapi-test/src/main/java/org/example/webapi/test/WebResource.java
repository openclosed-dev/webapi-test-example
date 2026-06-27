package org.example.webapi.test;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import tools.jackson.databind.annotation.JsonTypeIdResolver;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type",
    visible = true)
@JsonTypeIdResolver(WebResourceTypeIdResolver.class)
public interface WebResource {

    String type();

    String id();
}
