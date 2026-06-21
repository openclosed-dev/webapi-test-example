package org.example.webapi;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import tools.jackson.databind.annotation.JsonTypeIdResolver;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type",
    visible = false)
@JsonTypeIdResolver(WebResourceTypeIdResolver.class)
public interface WebResource {

    String id();
}
