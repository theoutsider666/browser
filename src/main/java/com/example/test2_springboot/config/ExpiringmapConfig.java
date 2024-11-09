package com.example.test2_springboot.config;

import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.concurrent.TimeUnit;

@Configuration
public class ExpiringmapConfig {
    @Lazy
    @Bean
    public ExpiringMap expiringMap() {
        ExpiringMap<String, Object> map = ExpiringMap.builder()
                .maxSize(Integer.MAX_VALUE)
                .expiration(24, TimeUnit.HOURS)
                .expirationPolicy(ExpirationPolicy.ACCESSED)
                .variableExpiration()
                .build();
        return map;
    }

    public static void main(String[] args) throws InterruptedException {
        ExpiringMap<String, String> map = ExpiringMap.builder()
                .maxSize(100)
                .expiration(1, TimeUnit.SECONDS)
                .expirationPolicy(ExpirationPolicy.ACCESSED)
                .variableExpiration()
                .build();
        map.put("test", "test123");
        Thread.sleep(500);
        String test = map.get("test");
        System.err.println(test);
    }
}
