package com.harmonycloud.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URI;
import java.net.URISyntaxException;

@ConfigurationProperties(prefix = "service")
public class EncounterConfigurationProperties {
    private String encounter;

    public String getEncounter() {
        return encounter;
    }

    public void setEncounter(String encounter) {
        this.encounter = encounter;
    }

    public URI getEncounterUri() {
        try {
            return new URI(encounter);
        } catch (URISyntaxException e) {
            return null;
        }
    }
}
