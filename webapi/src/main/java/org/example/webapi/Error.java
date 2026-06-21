package org.example.webapi;

import java.util.UUID;

public record Error(
    String id, String status, String detail) {

    public Error(String status, String detail) {
        this(UUID.randomUUID().toString(), status, detail);
    }
}
