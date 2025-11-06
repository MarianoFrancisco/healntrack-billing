package com.sa.healntrack.billing_service.billing.infrastructure.adapter.out.kafka.notification.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationRequestedMessage {

    private String requestId;
    private String to;
    private String toName;
    private String subject;
    private String bodyHtml;
}
