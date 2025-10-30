package com.sa.healntrack.billing_service.billing.infrastructure.adapter.in.kafka.events;

import com.sa.healntrack.billing_service.billing.domain.InvoiceData;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BillingRequestedEvent {
    public String requestId;
    public String subject;
    public String templateKey;
    public InvoiceData data;
}
