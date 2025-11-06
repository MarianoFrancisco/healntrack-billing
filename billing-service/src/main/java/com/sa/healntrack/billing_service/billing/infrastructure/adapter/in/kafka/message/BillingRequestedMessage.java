package com.sa.healntrack.billing_service.billing.infrastructure.adapter.in.kafka.message;

import com.sa.healntrack.billing_service.billing.infrastructure.adapter.in.kafka.message.dto.ItemDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record BillingRequestedMessage(
        String requestId,
        String subject,
        String templateKey,
        String title,
        String description,
        String email,
        String taxId,
        String name,
        List<ItemDTO> items,
        BigDecimal total,
        LocalDate date
) {
}
