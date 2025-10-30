package com.sa.healntrack.billing_service.billing.application.port.in.generate_invoice;

import com.sa.healntrack.billing_service.billing.domain.InvoiceData;
import com.sa.healntrack.billing_service.common.application.error.ErrorCode;
import com.sa.healntrack.billing_service.common.application.exception.DomainException;
import lombok.Getter;

@Getter
public final class GenerateInvoiceCommand {
    private final String requestId;
    private final String subject;
    private final String templateKey;
    private final InvoiceData data;

    public GenerateInvoiceCommand(String requestId, String subject, String templateKey, InvoiceData data) {
        if (requestId == null || requestId.isBlank()) throw new DomainException(ErrorCode.INVALID_REQUEST_ID);
        if (subject == null || subject.isBlank()) throw new DomainException(ErrorCode.EMPTY_SUBJECT);
        this.requestId = requestId;
        this.subject = subject;
        this.templateKey = templateKey;
        this.data = data;
    }
}
