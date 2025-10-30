package com.sa.healntrack.billing_service.billing.domain;

import com.sa.healntrack.billing_service.common.application.error.ErrorCode;
import com.sa.healntrack.billing_service.common.application.exception.DomainException;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public record User(String name, TaxId taxId, String email) {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");

    public User {
        if (!StringUtils.isNoneBlank(email)) {
            throw new DomainException(ErrorCode.EMPTY_USER_EMAIL);
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new DomainException(ErrorCode.INVALID_USER_EMAIL);
        }
        if (name == null) {
            name = email;
        }
    }
}
