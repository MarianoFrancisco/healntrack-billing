package com.sa.healntrack.billing_service.billing.infrastructure.adapter.out.notification;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sa.healntrack.billing_service.billing.application.port.out.NotificationPublisher;
import com.sa.healntrack.billing_service.common.application.error.ErrorCode;
import com.sa.healntrack.billing_service.common.application.exception.TransientInfrastructureException;
import com.sa.healntrack.billing_service.common.infrastructure.config.NotificationTopicProperties;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaNotificationPublisher implements NotificationPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final NotificationTopicProperties notificationTopics;
    private final ObjectMapper objectMapper;

    @Override
    public void publish(String requestId, String to, String toName, String subject, String bodyHtml) {
        try {
            NotificationRequested event = new NotificationRequested(
                    requestId, to, toName, subject, bodyHtml
            );

            String json = objectMapper.writeValueAsString(event);
            ProducerRecord<String, String> record =
                    new ProducerRecord<>(notificationTopics.getRequested(), requestId, json);
            kafkaTemplate.send(record);
        } catch (Exception ex) {
            throw new TransientInfrastructureException(ErrorCode.KAFKA_PUBLISH_ERROR, ex);
        }
    }
}
