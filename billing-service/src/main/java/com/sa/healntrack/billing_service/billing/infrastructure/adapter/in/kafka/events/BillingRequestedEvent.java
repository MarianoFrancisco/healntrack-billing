package com.sa.healntrack.billing_service.billing.infrastructure.adapter.in.kafka.events;

import com.sa.healntrack.billing_service.billing.domain.InvoiceData;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BillingRequestedEvent {

    private String requestId;
    private String subject;
    private String templateKey;
    private InvoiceData data;
}
