package com.sa.healntrack.billing_service.common.application.error;

import java.util.Map;

public final class ErrorCatalog {

    private static final Map<ErrorCode, String> MESSAGES = Map.ofEntries(
            Map.entry(ErrorCode.INVALID_REQUEST_ID, "El identificador de solicitud es obligatorio."),
            Map.entry(ErrorCode.EMPTY_SUBJECT, "El asunto del mensaje no puede estar vacío."),

            Map.entry(ErrorCode.EMPTY_TAX_ID, "El NIT no puede estar vacío."),
            Map.entry(ErrorCode.INVALID_TAX_ID, "El NIT proporcionado no tiene un formato válido."),

            Map.entry(ErrorCode.EMPTY_ITEM_NAME, "El nombre del producto o servicio no puede estar vacío."),
            Map.entry(ErrorCode.INVALID_ITEM_QTY, "La cantidad de un ítem debe ser mayor que cero."),
            Map.entry(ErrorCode.INVALID_ITEM_PRICE, "El precio unitario no puede ser negativo."),
            Map.entry(ErrorCode.INVALID_ITEM_SUBTOTAL, "El subtotal del ítem no puede ser negativo."),
            Map.entry(ErrorCode.INCONSISTENT_ITEM_TOTAL, "El subtotal del ítem no coincide con la cantidad y el precio."),

            Map.entry(ErrorCode.EMPTY_USER, "Debe especificarse la información del usuario."),
            Map.entry(ErrorCode.EMPTY_USER_EMAIL, "El correo electrónico del usuario no puede estar vacío."),
            Map.entry(ErrorCode.INVALID_USER_EMAIL, "El correo electrónico del usuario no tiene un formato válido."),

            Map.entry(ErrorCode.EMPTY_INVOICE_TITLE, "El título de la factura no puede estar vacío."),
            Map.entry(ErrorCode.EMPTY_INVOICE_DESCRIPTION, "La descripción de la factura no puede estar vacía."),
            Map.entry(ErrorCode.EMPTY_ITEMS, "Debe contener al menos un ítem de facturación."),
            Map.entry(ErrorCode.INVALID_TOTAL, "El total de la factura no puede ser negativo."),
            Map.entry(ErrorCode.EMPTY_DATE, "Debe indicarse la fecha de emisión de la factura."),

            Map.entry(ErrorCode.DESERIALIZATION_ERROR, "No se pudo interpretar el evento de facturación recibido."),
            Map.entry(ErrorCode.TEMPLATE_RENDER_ERROR, "Ocurrió un error al generar el HTML de la factura."),
            Map.entry(ErrorCode.KAFKA_PUBLISH_ERROR, "No se pudo publicar la notificación de factura."),
            Map.entry(ErrorCode.UNKNOWN_ERROR, "Ha ocurrido un error inesperado.")
    );

    private ErrorCatalog() {
    }

    public static String messageFor(ErrorCode code) {
        return MESSAGES.getOrDefault(code, "Error desconocido.");
    }
}
