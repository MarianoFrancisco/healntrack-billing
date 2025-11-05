package com.sa.healntrack.billing_service.billing.infrastructure.adapter.out.template;

import com.fasterxml.jackson.databind.JsonNode;
import com.sa.healntrack.billing_service.billing.application.port.out.HtmlTemplateRenderer;
import com.sa.healntrack.billing_service.billing.domain.InvoiceData;
import com.sa.healntrack.billing_service.common.application.error.ErrorCode;
import com.sa.healntrack.billing_service.common.application.exception.PermanentInfrastructureException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
@RequiredArgsConstructor
public class ThymeleafTemplateRenderer implements HtmlTemplateRenderer {

    private final TemplateEngine templateEngine;

    @Override
    public String render(String key, InvoiceData data) {
        try {
            Context ctx = new Context();
            ctx.setVariable("it", data);
            return templateEngine.process(key, ctx);
        } catch (Exception ex) {
            throw new PermanentInfrastructureException(ErrorCode.TEMPLATE_RENDER_ERROR, ex);
        }
    }
}
