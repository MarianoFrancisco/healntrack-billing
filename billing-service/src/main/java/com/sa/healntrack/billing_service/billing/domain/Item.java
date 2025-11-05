package com.sa.healntrack.billing_service.billing.domain;

import com.sa.healntrack.billing_service.common.application.error.ErrorCode;
import com.sa.healntrack.billing_service.common.application.exception.DomainException;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

public record Item(String name, int qty, BigDecimal price, BigDecimal subtotal) {

    public Item {
        if (!StringUtils.isNoneBlank(name)) {
            throw new DomainException(ErrorCode.EMPTY_ITEM_NAME);
        }
        if (qty <= 0) {
            throw new DomainException(ErrorCode.INVALID_ITEM_QTY);
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new DomainException(ErrorCode.INVALID_ITEM_PRICE);
        }
        if (subtotal == null || subtotal.compareTo(BigDecimal.ZERO) < 0) {
            throw new DomainException(ErrorCode.INVALID_ITEM_SUBTOTAL);
        }

        BigDecimal expected = price.multiply(BigDecimal.valueOf(qty));
        if (expected.compareTo(subtotal) != 0) {
            throw new DomainException(ErrorCode.INCONSISTENT_ITEM_TOTAL);
        }
    }
}
