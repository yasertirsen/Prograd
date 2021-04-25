package com.fyp.prograd.utilities;

import lombok.SneakyThrows;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("spsb")
public class StudentPortalBackendHealthIndicator implements HealthIndicator {

    private final HealthTester healthTester;

    public StudentPortalBackendHealthIndicator(HealthTester healthTester) {
        this.healthTester = healthTester;
    }

    @SneakyThrows
    @Override
    public Health health() {
        return healthTester.getHealth("http://localhost:8083/");
    }
}
