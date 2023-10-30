package com.muscatlab.memberservice.adapter.in.web.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("MemberResponse 테스트")
class MemberResponseTest {
    private UUID id;

    private String email;

    private String name;

    @BeforeEach()
    void setUp() {
        id = UUID.randomUUID();
        email = "email";
        name = "name";
    }

    @Nested
    @DisplayName("생성자 테스트")
    class ConstructorTest {
        @Test
        @DisplayName("builder 테스트")
        void builder() {
            // when
            var result = MemberResponse.builder()
                    .id(id)
                    .email(email)
                    .name(name)
                    .build();
            // then
            assertNotNull(result);
            assertEquals(result.id(), id);
            assertEquals(result.email(), email);
            assertEquals(result.name(), name);
        }
    }
}