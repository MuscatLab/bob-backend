package com.muscatlab.authservice.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtPropertiesConfig {
    @Getter
    @Value("${security.jwt.token.secretKey}")
    private String secretKey;

    @Value("${security.jwt.token.tokenExpirationInHours}")
    private long tokenExpirationInHours;

    public long getAccessTokenExpirationInHours() {
        return tokenExpirationInHours;
    }

    public long getRefreshTokenExpirationInHours() {
        return getAccessTokenExpirationInHours() + 36L;
    }
}
