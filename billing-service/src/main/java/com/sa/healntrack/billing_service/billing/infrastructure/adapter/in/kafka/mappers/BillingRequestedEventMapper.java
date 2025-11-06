package com.sa.healntrack.billing_service.billing.infrastructure.adapter.in.kafka.mappers;

import com.sa.healntrack.billing_service.billing.application.port.in.generate_invoice.GenerateInvoiceCommand;
import com.sa.healntrack.billing_service.billing.application.port.in.generate_invoice.Item;
import com.sa.healntrack.billing_service.billing.infrastructure.adapter.in.kafka.message.BillingRequestedMessage;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BillingRequestedEventMapper {

    public GenerateInvoiceCommand toCommand(BillingRequestedMessage e) {
        return new GenerateInvoiceCommand(
                e.requestId(),
                e.subject(),
                e.templateKey(),
                e.title(),
                e.description(),
                e.email(),
                e.taxId(),
                e.name(),
                e.items().stream()
                        .map(i -> new Item(
                                i.name(),
                                i.qty(),
                                i.price(),
                                i.subtotal()
                        ))
                        .toList(),
                e.total(),
                e.date()
        );
    }
}
