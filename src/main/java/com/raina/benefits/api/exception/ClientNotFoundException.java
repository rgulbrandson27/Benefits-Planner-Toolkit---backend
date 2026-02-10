package com.raina.benefits.api.exception;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(Long id) {
        super("Client not found (id=" + id + ")");
    }
}