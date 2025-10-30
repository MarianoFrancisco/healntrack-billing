package com.sa.healntrack.billing_service.billing.domain;

import com.sa.healntrack.billing_service.common.application.error.ErrorCode;
import com.sa.healntrack.billing_service.common.application.exception.DomainException;

import java.util.regex.Pattern;

public record TaxId(String value) {

    private static final Pattern VALID = Pattern.compile("(?i)(\\d{13}|\\d{7,10}-?[0-9kK]|c/?f)");

    public TaxId {
        String normalized = (value == null || value.isBlank())
                ? "C/F"
                : value.trim().toUpperCase();

        if (!VALID.matcher(normalized).matches()) {
            throw new DomainException(ErrorCode.INVALID_TAX_ID);
        }

        value = normalized;
    }
}
