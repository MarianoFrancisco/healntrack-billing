package com.sa.healntrack.billing_service.billing.application.port.out;

import com.sa.healntrack.billing_service.billing.domain.InvoiceData;

public interface HtmlTemplateRenderer {
    String render(String key, InvoiceData data);
}
