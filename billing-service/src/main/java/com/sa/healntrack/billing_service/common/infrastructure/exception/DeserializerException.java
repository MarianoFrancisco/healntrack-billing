package com.sa.healntrack.billing_service.common.infrastructure.exception;

public class DeserializerException extends RuntimeException {
    public DeserializerException(String error) {
        super("Error al deserializar la clase: " + error);
    }
}
