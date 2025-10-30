package com.sa.healntrack.billing_service.billing.domain;

import com.sa.healntrack.billing_service.common.application.error.ErrorCode;
import com.sa.healntrack.billing_service.common.application.exception.DomainException;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record InvoiceData(
        String title,
        String description,
        User user,
        List<Item> items,
        BigDecimal total,
        LocalDate date
) {

    public InvoiceData {
        if (!StringUtils.isNoneBlank(title)) {
            throw new DomainException(ErrorCode.EMPTY_INVOICE_TITLE);
        }
        if (description != null && description.isBlank()) {
            throw new DomainException(ErrorCode.EMPTY_INVOICE_DESCRIPTION);
        }
        if (user == null) {
            throw new DomainException(ErrorCode.EMPTY_USER);
        }
        if (items == null || items.isEmpty()) {
            throw new DomainException(ErrorCode.EMPTY_ITEMS);
        }
        if (total == null || total.compareTo(BigDecimal.ZERO) < 0) {
            throw new DomainException(ErrorCode.INVALID_TOTAL);
        }
        if (date == null) {
            throw new DomainException(ErrorCode.EMPTY_DATE);
        }
    }
}
