package com.sa.healntrack.billing_service.common.infrastructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "billing.topic")
public class BillingTopicProperties {
    private String requested = "billing.requested";
}
