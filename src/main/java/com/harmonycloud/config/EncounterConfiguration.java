package com.harmonycloud.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncounterConfiguration {
    @Bean
    @ConditionalOnMissingBean(EncounterConfigurationProperties.class)
    public EncounterConfigurationProperties configProperties() {
        return new EncounterConfigurationProperties();
    }
}
