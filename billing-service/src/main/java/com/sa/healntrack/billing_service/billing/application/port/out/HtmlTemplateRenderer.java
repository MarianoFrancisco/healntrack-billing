package com.sa.healntrack.billing_service.billing.application.port.out;

import com.sa.healntrack.billing_service.billing.application.port.in.generate_invoice.GenerateInvoiceCommand;

public interface HtmlTemplateRenderer {
    String render(String key, GenerateInvoiceCommand data);
}
