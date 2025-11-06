package com.sa.healntrack.billing_service.common.infrastructure.exception;

public class SerializerException extends RuntimeException {
    public SerializerException(String error) {
        super("Error al serializar la clase: " + error);
    }
}
