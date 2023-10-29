package com.muscatlab.memberservice.application.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Member 도메인 테스트")
class MemberTest {
    private Member member;

    private String name;
    private String email;
    private String password;

    @BeforeEach
    void setUp() {
        member = createMember();
    }

    @Test
    @DisplayName("비밀번호 변경 테스트")
    void updatePassword() {
        String newPassword = "newPassword";
        member.updatePassword(newPassword);
        assertTrue(member.validatePassword(newPassword));
    }

    @Test
    @DisplayName("비밀번호 검증 테스트")
    void validatePassword() {
        assertTrue(member.validatePassword(password));
    }

    @Nested
    @DisplayName("Member 생성 테스트")
    class BuildMember {
        @Test
        @DisplayName("Member 생성 테스트")
        void buildMember() {
            Member member = createMember();
            assertTrue(member.getName().equals(name));
            assertTrue(member.getEmail().equals(email));
            assertNotNull(member.getSalt());
            assertTrue(member.validatePassword(password));
        }

        @Test
        @DisplayName("Member 생성 테스트 - 이름이 null일 경우")
        void buildMemberWithNullName() {
            name = null;
            NullPointerException nullPointerException = assertThrows(
                    NullPointerException.class, () -> {
                        Member.builder()
                                .email(email)
                                .password(password)
                                .build();
                    });

            assertTrue(nullPointerException.getMessage().contains("name"));
        }

        @Test
        @DisplayName("Member 생성 테스트 - 이메일이 null일 경우")
        void buildMemberWithNullEmail() {
            email = null;
            NullPointerException nullPointerException = assertThrows(
                    NullPointerException.class, () -> {
                        Member.builder()
                                .name(name)
                                .password(password)
                                .build();
                    });

            assertTrue(nullPointerException.getMessage().contains("email"));
        }

        @Test
        @DisplayName("Member 생성 테스트 - 비밀번호가 null일 경우")
        void buildMemberWithNullPassword() {
            password = null;
            NullPointerException nullPointerException = assertThrows(
                    NullPointerException.class, () -> {
                        Member.builder()
                                .name(name)
                                .email(email)
                                .build();
                    });

            assertTrue(nullPointerException.getMessage().contains("password"));
        }
    }

    private Member createMember() {
        name = "name";
        email = "email";
        password = "password";
        return Member.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}