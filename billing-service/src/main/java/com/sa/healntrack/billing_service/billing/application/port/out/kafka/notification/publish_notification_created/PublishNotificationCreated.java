package com.sa.healntrack.billing_service.billing.application.port.out.kafka.notification.publish_notification_created;

public interface PublishNotificationCreated {
    void publish(PublishNotificationCreatedCommand command);
}
