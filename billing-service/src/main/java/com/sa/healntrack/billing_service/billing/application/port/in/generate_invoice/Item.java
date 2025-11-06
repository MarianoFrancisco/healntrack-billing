package com.sa.healntrack.billing_service.billing.application.port.in.generate_invoice;

import java.math.BigDecimal;

public record Item(String name, int qty, BigDecimal price, BigDecimal subtotal) {
}
