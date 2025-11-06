package com.sa.healntrack.billing_service.billing.infrastructure.adapter.in.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sa.healntrack.billing_service.billing.application.port.in.generate_invoice.GenerateInvoiceAndNotify;
import com.sa.healntrack.billing_service.billing.application.port.in.generate_invoice.GenerateInvoiceCommand;
import com.sa.healntrack.billing_service.billing.infrastructure.adapter.in.kafka.message.BillingRequestedMessage;
import com.sa.healntrack.billing_service.billing.infrastructure.adapter.in.kafka.mappers.BillingRequestedEventMapper;
import com.sa.healntrack.billing_service.common.infrastructure.exception.DeserializerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class BillingConsumer {

    private final GenerateInvoiceAndNotify useCase;
    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = "#{@billingTopicProperties.requested}",
            groupId = "#{@kafkaConsumerProperties.groupId}"
    )
    public void consume(ConsumerRecord<String, byte[]> record) {
        try {
            BillingRequestedMessage event = objectMapper.readValue(
                    record.value(),
                    BillingRequestedMessage.class
            );

            GenerateInvoiceCommand cmd = BillingRequestedEventMapper.toCommand(event);
            useCase.handle(cmd);
        } catch (IOException ex) {
            log.error("DESERIALIZATION_ERROR offset={}", record.offset(), ex);
            throw new DeserializerException("BillingRequestedMessage");
        }
    }
}
