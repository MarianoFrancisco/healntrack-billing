package com.sa.healntrack.billing_service.billing.infrastructure.adapter.in.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sa.healntrack.billing_service.billing.application.port.in.generate_invoice.GenerateInvoiceAndNotify;
import com.sa.healntrack.billing_service.billing.application.port.in.generate_invoice.GenerateInvoiceCommand;
import com.sa.healntrack.billing_service.billing.infrastructure.adapter.in.kafka.events.BillingRequestedEvent;
import com.sa.healntrack.billing_service.billing.infrastructure.adapter.in.kafka.mappers.BillingRequestedEventMapper;
import com.sa.healntrack.billing_service.common.application.error.ErrorCode;
import com.sa.healntrack.billing_service.common.application.exception.DomainException;
import com.sa.healntrack.billing_service.common.application.exception.PermanentInfrastructureException;
import com.sa.healntrack.billing_service.common.application.exception.TransientInfrastructureException;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BillingKafkaConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(BillingKafkaConsumer.class);

    private final GenerateInvoiceAndNotify useCase;
    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = "#{@billingTopicProperties.requested}",
            groupId = "#{@kafkaConsumerProperties.groupId}",
            containerFactory = "billingKafkaListenerContainerFactory"
    )
    public void onMessage(ConsumerRecord<String, String> record) {
        BillingRequestedEvent event;
        try {
            event = objectMapper.readValue(record.value(), BillingRequestedEvent.class);
        } catch (Exception ex) {
            LOG.error("DESERIALIZATION_ERROR offset={}", record.offset(), ex);
            return;
        }

        try {
            GenerateInvoiceCommand cmd = BillingRequestedEventMapper.toCommand(event);
            useCase.handle(cmd);

        } catch (DomainException ex) {
            LOG.warn("DOMAIN_ERROR code={} requestId={} offset={}", ex.getCode().name(), event.requestId, record.offset());

        } catch (Exception ex) {
            LOG.error("UNKNOWN_ERROR requestId={} offset={} (will retry)", event.requestId, record.offset(), ex);
            throw new TransientInfrastructureException(ErrorCode.UNKNOWN_ERROR, ex);
        }
    }
}
