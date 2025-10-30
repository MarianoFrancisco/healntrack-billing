package com.sa.healntrack.billing_service.billing.infrastructure.adapter.in.kafka.mappers;

import com.sa.healntrack.billing_service.billing.application.port.in.generate_invoice.GenerateInvoiceCommand;
import com.sa.healntrack.billing_service.billing.infrastructure.adapter.in.kafka.events.BillingRequestedEvent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BillingRequestedEventMapper {

    public static GenerateInvoiceCommand toCommand(BillingRequestedEvent e) {
        return new GenerateInvoiceCommand(
                e.requestId,
                e.subject,
                e.templateKey,
                e.data
        );
    }
}
