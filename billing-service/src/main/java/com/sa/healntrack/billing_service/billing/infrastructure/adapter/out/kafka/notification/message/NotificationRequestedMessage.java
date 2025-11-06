package com.sa.healntrack.billing_service.billing.infrastructure.adapter.out.kafka.notification.message;

public record NotificationRequestedMessage(
        String requestId,
        String to,
        String toName,
        String subject,
        String bodyHtml
) {
}
