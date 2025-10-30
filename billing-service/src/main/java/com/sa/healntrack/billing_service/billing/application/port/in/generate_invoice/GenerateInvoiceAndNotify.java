package com.sa.healntrack.billing_service.billing.application.port.in.generate_invoice;

public interface GenerateInvoiceAndNotify {
    void handle(GenerateInvoiceCommand command);
}
