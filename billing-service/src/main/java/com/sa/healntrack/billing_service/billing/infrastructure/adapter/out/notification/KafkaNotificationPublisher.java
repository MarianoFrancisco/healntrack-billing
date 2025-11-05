package com.sa.healntrack.billing_service.billing.infrastructure.adapter.out.notification;

import com.sa.healntrack.billing_service.billing.application.port.out.NotificationPublisher;
import com.sa.healntrack.billing_service.common.application.error.ErrorCode;
import com.sa.healntrack.billing_service.common.application.exception.TransientInfrastructureException;
import com.sa.healntrack.billing_service.common.infrastructure.config.NotificationTopicProperties;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@RequiredArgsConstructor
public class KafkaNotificationPublisher implements NotificationPublisher {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;
    private final NotificationTopicProperties notificationTopics;
    private final ObjectMapper objectMapper;

    @Override
    public void publish(String requestId, String to, String toName, String subject, String bodyHtml) {
        try {
            NotificationRequested event = new NotificationRequested(
                    requestId, to, toName, subject, bodyHtml
            );

            byte[] eventBytes = objectMapper.writeValueAsBytes(event);

            ProducerRecord<String, byte[]> record =
                    new ProducerRecord<>(notificationTopics.getRequested(), requestId, eventBytes);
            kafkaTemplate.send(record);
        } catch (Exception ex) {
            throw new TransientInfrastructureException(ErrorCode.KAFKA_PUBLISH_ERROR, ex);
        }
    }
}
