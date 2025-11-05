package com.sa.healntrack.billing_service.billing.infrastructure.adapter.out.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationRequested {

    private String requestId;
    private String to;
    private String toName;
    private String subject;
    private String bodyHtml;
}
