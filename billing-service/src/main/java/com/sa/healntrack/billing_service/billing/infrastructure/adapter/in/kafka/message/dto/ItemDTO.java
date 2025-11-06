package com.sa.healntrack.billing_service.billing.infrastructure.adapter.in.kafka.message.dto;

import java.math.BigDecimal;

public record ItemDTO(String name, int qty, BigDecimal price, BigDecimal subtotal) {
}
