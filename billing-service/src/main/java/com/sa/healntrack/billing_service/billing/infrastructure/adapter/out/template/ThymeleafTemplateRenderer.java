package com.sa.healntrack.billing_service.billing.infrastructure.adapter.out.template;

import com.sa.healntrack.billing_service.billing.application.port.in.generate_invoice.GenerateInvoiceCommand;
import com.sa.healntrack.billing_service.billing.application.port.out.HtmlTemplateRenderer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
@RequiredArgsConstructor
public class ThymeleafTemplateRenderer implements HtmlTemplateRenderer {

    private final TemplateEngine templateEngine;

    @Override
    public String render(String key, GenerateInvoiceCommand data) {
        try {
            Context ctx = new Context();
            ctx.setVariable("it", data);
            return templateEngine.process(key, ctx);
        } catch (Exception e) {
            throw new RuntimeException("Error renderizando la plantilla con key: " + key, e);
        }
    }
}
