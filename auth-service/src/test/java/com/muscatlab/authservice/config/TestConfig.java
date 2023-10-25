package com.muscatlab.authservice.config;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
@ExtendWith({MockitoExtension.class})
public class TestConfig {

    @Bean
    public JwtPropertiesConfig jwtPropertiesConfig() {
        JwtPropertiesConfig mockJwtPropertiesConfig = new JwtPropertiesConfig();
        return mockJwtPropertiesConfig;
    }
}
