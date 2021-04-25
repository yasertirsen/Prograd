package com.fyp.prograd.utilities;

import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class HealthTester {

    public Health getHealth(String url) {
        Health.Builder status = new Health.Builder();
        try {
            URL siteURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);
            connection.connect();

            int code = connection.getResponseCode();
            if (code == 200) {
                status = Health.up();
            }
        } catch (Exception e) {
            status = Health.down();
        }
        return status.build();
    }
}
