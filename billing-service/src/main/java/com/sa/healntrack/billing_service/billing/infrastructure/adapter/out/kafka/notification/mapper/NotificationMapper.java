package com.sa.healntrack.billing_service.billing.infrastructure.adapter.out.kafka.notification.mapper;

import com.sa.healntrack.billing_service.billing.application.port.out.kafka.notification.publish_notification_created.PublishNotificationCreatedCommand;
import com.sa.healntrack.billing_service.billing.infrastructure.adapter.out.kafka.notification.message.NotificationRequestedMessage;
import lombok.experimental.UtilityClass;

@UtilityClass
public class NotificationMapper {

    public NotificationRequestedMessage toMessage(PublishNotificationCreatedCommand command) {
        return NotificationRequestedMessage.builder()
                .requestId(command.requestId())
                .to(command.to())
                .toName(command.toName())
                .subject(command.subject())
                .bodyHtml(command.bodyHtml())
                .build();
    }
}