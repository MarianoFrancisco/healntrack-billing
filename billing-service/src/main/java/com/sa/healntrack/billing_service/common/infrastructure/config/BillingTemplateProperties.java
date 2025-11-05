package com.sa.healntrack.billing_service.common.infrastructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "billing.template")
public class BillingTemplateProperties {
    private String defaultKey = "invoice/default";
}
