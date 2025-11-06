package com.sa.healntrack.billing_service.billing.infrastructure.adapter.out.kafka.notification;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sa.healntrack.billing_service.billing.application.port.out.kafka.notification.publish_notification_created.PublishNotificationCreated;
import com.sa.healntrack.billing_service.billing.application.port.out.kafka.notification.publish_notification_created.PublishNotificationCreatedCommand;
import com.sa.healntrack.billing_service.billing.infrastructure.adapter.out.kafka.notification.mapper.NotificationMapper;
import com.sa.healntrack.billing_service.billing.infrastructure.adapter.out.kafka.notification.message.NotificationRequestedMessage;
import com.sa.healntrack.billing_service.common.infrastructure.config.NotificationTopicProperties;
import com.sa.healntrack.billing_service.common.infrastructure.exception.SerializerException;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class NotificationPublisher implements PublishNotificationCreated {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;
    private final NotificationTopicProperties notificationTopics;
    private final ObjectMapper objectMapper;

    @Override
    public void publish(PublishNotificationCreatedCommand cmd) {
        try {
            NotificationRequestedMessage notificationRequestedMessage = NotificationMapper.toMessage(cmd);

            byte[] eventBytes = objectMapper.writeValueAsBytes(notificationRequestedMessage);

            ProducerRecord<String, byte[]> record =
                    new ProducerRecord<>(notificationTopics.getRequested(), notificationRequestedMessage.requestId(), eventBytes);
            kafkaTemplate.send(record);
        } catch (JsonProcessingException ex) {
            throw new SerializerException("NotificationRequestedMessage");
        }
    }
}
