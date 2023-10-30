package com.muscatlab.memberservice.application.port.in;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CreateMemberCommand 테스트")
class CreateMemberCommandTest {
    private String email;

    private String name;

    private String password;


    @BeforeEach
    void setUp() {
        email = "email";
        name = "name";
        password = "password";
    }

    @Nested
    @DisplayName("생성자 테스트")
    class ConstructorTest {
        @Test
        @DisplayName("builder 테스트")
        void builder() {
            // when
            var result = CreateMemberCommand.builder()
                    .email(email)
                    .name(name)
                    .password(password)
                    .build();
            // then
            assertNotNull(result);
            assertEquals(result.email(), email);
            assertEquals(result.name(), name);
            assertEquals(result.password(), password);
        }

        @Test
        @DisplayName("Email이 null일 경우 예외 발생")
        void emailIsNull() {
            // when
            var result = assertThrows(NullPointerException.class, () -> CreateMemberCommand.builder()
                    .name(name)
                    .password(password)
                    .build());
            // then
            assertEquals(result.getMessage(), "email is marked non-null but is null");
        }

        @Test
        @DisplayName("Name이 null일 경우 예외 발생")
        void nameIsNull() {
            // when
            var result = assertThrows(NullPointerException.class, () -> CreateMemberCommand.builder()
                    .email(email)
                    .password(password)
                    .build());
            // then
            assertEquals(result.getMessage(), "name is marked non-null but is null");
        }

        @Test
        @DisplayName("Password가 null일 경우 예외 발생")
        void passwordIsNull() {
            // when
            var result = assertThrows(NullPointerException.class, () -> CreateMemberCommand.builder()
                    .email(email)
                    .name(name)
                    .build());
            // then
            assertEquals(result.getMessage(), "password is marked non-null but is null");
        }
    }

    @Test
    @DisplayName("toEntity 테스트")
    void toEntity() {
        // given
        CreateMemberCommand createMemberCommand = CreateMemberCommand.builder()
                .email(email)
                .name(name)
                .password(password)
                .build();
        // when
        var result = createMemberCommand.toEntity();
        // then
        assertNotNull(result);
        assertEquals(result.getEmail(), email);
        assertEquals(result.getName(), name);
    }
}