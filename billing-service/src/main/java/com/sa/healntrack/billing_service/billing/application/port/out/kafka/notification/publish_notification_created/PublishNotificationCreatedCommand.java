package com.sa.healntrack.billing_service.billing.application.port.out.kafka.notification.publish_notification_created;

public record PublishNotificationCreatedCommand(
        String requestId,
        String to,
        String toName,
        String subject,
        String bodyHtml
) {
}
