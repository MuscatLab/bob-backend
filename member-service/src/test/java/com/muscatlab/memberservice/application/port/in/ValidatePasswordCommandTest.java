package com.muscatlab.memberservice.application.port.in;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ValidatePasswordCommand 테스트")
class ValidatePasswordCommandTest {
    private String email;

    private String password;

    @BeforeEach
    void setUp() {
        email = "email";
        password = "password";
    }

    @Nested
    @DisplayName("생성자 테스트")
    class ConstructorTest {
        @Test
        @DisplayName("builder 테스트")
        void builder() {
            // when
            var result = ValidatePasswordCommand.builder()
                    .email(email)
                    .password(password)
                    .build();
            // then
            assertNotNull(result);
            assertEquals(result.email(), email);
            assertEquals(result.password(), password);
        }

        @Test
        @DisplayName("Email이 null일 경우 예외 발생")
        void emailIsNull() {
            // when
            var result = assertThrows(NullPointerException.class, () -> ValidatePasswordCommand.builder()
                    .password(password)
                    .build());
            // then
            assertEquals(result.getMessage(), "email is marked non-null but is null");
        }

        @Test
        @DisplayName("Password가 null일 경우 예외 발생")
        void passwordIsNull() {
            // when
            var result = assertThrows(NullPointerException.class, () -> ValidatePasswordCommand.builder()
                    .email(email)
                    .build());
            // then
            assertEquals(result.getMessage(), "password is marked non-null but is null");
        }
    }
}