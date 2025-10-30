package com.sa.healntrack.billing_service.billing.application.port.out;

public interface NotificationPublisher {
    void publish(String requestId, String to, String toName, String subject, String bodyHtml);
}
