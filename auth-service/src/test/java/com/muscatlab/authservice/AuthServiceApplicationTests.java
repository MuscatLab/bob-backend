package com.muscatlab.authservice;

import com.muscatlab.authservice.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;

@Import({TestConfig.class})
class AuthServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
