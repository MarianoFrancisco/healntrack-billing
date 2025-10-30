package com.sa.healntrack.billing_service.billing.application.service;

import com.sa.healntrack.billing_service.billing.application.port.in.generate_invoice.GenerateInvoiceAndNotify;
import com.sa.healntrack.billing_service.billing.application.port.in.generate_invoice.GenerateInvoiceCommand;
import com.sa.healntrack.billing_service.billing.application.port.out.HtmlTemplateRenderer;
import com.sa.healntrack.billing_service.billing.application.port.out.NotificationPublisher;
import com.sa.healntrack.billing_service.common.infrastructure.config.BillingTemplateProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

@Service
@RequiredArgsConstructor
public class BillingServiceImpl implements GenerateInvoiceAndNotify {

    private final HtmlTemplateRenderer templateRenderer;
    private final NotificationPublisher notificationPublisher;
    private final BillingTemplateProperties templateProperties;

    @Override
    public void handle(GenerateInvoiceCommand cmd) {
        String effectiveKey = StringUtils.isNoneBlank(cmd.getTemplateKey())
                ? cmd.getTemplateKey()
                : templateProperties.getDefaultKey();

        String html = templateRenderer.render(effectiveKey, cmd.getData());

        notificationPublisher.publish(
                cmd.getRequestId(),
                cmd.getData().user().email(),
                cmd.getData().user().name(),
                cmd.getSubject(),
                html
        );
    }
}
