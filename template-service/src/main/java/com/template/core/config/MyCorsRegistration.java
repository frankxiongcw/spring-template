package com.template.core.config;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;

public class MyCorsRegistration extends CorsRegistration {

    public MyCorsRegistration(String pathPattern) {
        super(pathPattern);
    }

    @Override
    public CorsConfiguration getCorsConfiguration() {
        return super.getCorsConfiguration();
    }
}
