package com.sa.healntrack.billing_service.billing.infrastructure.adapter.in.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sa.healntrack.billing_service.billing.application.port.in.generate_invoice.GenerateInvoiceAndNotify;
import com.sa.healntrack.billing_service.billing.application.port.in.generate_invoice.GenerateInvoiceCommand;
import com.sa.healntrack.billing_service.billing.infrastructure.adapter.in.kafka.events.BillingRequestedEvent;
import com.sa.healntrack.billing_service.billing.infrastructure.adapter.in.kafka.mappers.BillingRequestedEventMapper;
import com.sa.healntrack.billing_service.common.application.error.ErrorCode;
import com.sa.healntrack.billing_service.common.application.exception.DomainException;
import com.sa.healntrack.billing_service.common.application.exception.TransientInfrastructureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BillingKafkaConsumer {

    private final GenerateInvoiceAndNotify useCase;
    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = "#{@billingTopicProperties.requested}",
            groupId = "#{@kafkaConsumerProperties.groupId}"
    )
    public void onMessage(ConsumerRecord<String, byte[]> record) {
        BillingRequestedEvent event = null;
        try {
            event = objectMapper.readValue(record.value(), BillingRequestedEvent.class);

            GenerateInvoiceCommand cmd = BillingRequestedEventMapper.toCommand(event);
            useCase.handle(cmd);
        } catch (JsonProcessingException | IllegalArgumentException ex) {
            log.error("DESERIALIZATION_ERROR offset={}", record.offset(), ex);
        } catch (DomainException ex) {
            String reqId = (event != null ? event.getRequestId() : "unknown");
            log.warn("DOMAIN_ERROR code={} requestId={} offset={}",
                    ex.getCode().name(), reqId, record.offset());
        } catch (Exception ex) {
            String reqId = (event != null ? event.getRequestId() : "unknown");
            log.error("UNKNOWN_ERROR requestId={} offset={} (will retry)",
                    reqId, record.offset(), ex);
            throw new TransientInfrastructureException(ErrorCode.UNKNOWN_ERROR, ex);
        }
    }
}
