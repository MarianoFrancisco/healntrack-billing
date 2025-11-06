package com.sa.healntrack.billing_service.billing.application.service;

import com.sa.healntrack.billing_service.billing.application.port.in.generate_invoice.GenerateInvoiceAndNotify;
import com.sa.healntrack.billing_service.billing.application.port.in.generate_invoice.GenerateInvoiceCommand;
import com.sa.healntrack.billing_service.billing.application.port.out.HtmlTemplateRenderer;
import com.sa.healntrack.billing_service.billing.application.port.out.kafka.notification.publish_notification_created.PublishNotificationCreated;
import com.sa.healntrack.billing_service.billing.application.port.out.kafka.notification.publish_notification_created.PublishNotificationCreatedCommand;
import com.sa.healntrack.billing_service.common.infrastructure.config.BillingTemplateProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BillingServiceImpl implements GenerateInvoiceAndNotify {

    private final HtmlTemplateRenderer templateRenderer;
    private final PublishNotificationCreated notificationPublisher;
    private final BillingTemplateProperties templateProperties;

    @Override
    public void handle(GenerateInvoiceCommand cmd) {
        String effectiveKey = StringUtils.isNoneBlank(cmd.templateKey())
                ? cmd.templateKey()
                : templateProperties.getDefaultKey();

        String html = templateRenderer.render(effectiveKey, cmd);

        notificationPublisher.publish(
                new PublishNotificationCreatedCommand(
                        cmd.requestId(),
                        cmd.email(),
                        cmd.name(),
                        cmd.subject(),
                        html
                )
        );
    }
}
