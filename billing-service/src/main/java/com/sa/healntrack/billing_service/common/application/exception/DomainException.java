package com.sa.healntrack.billing_service.common.application.exception;

import com.sa.healntrack.billing_service.common.application.error.ErrorCode;
import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {
    private final ErrorCode code;

    public DomainException(ErrorCode code) {
        this.code = code;
    }

    public DomainException(ErrorCode code, Throwable cause) {
        super(cause);
        this.code = code;
    }
}
