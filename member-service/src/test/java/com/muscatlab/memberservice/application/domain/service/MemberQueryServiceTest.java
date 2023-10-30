package com.muscatlab.memberservice.application.domain.service;

import com.muscatlab.memberservice.application.domain.model.Member;
import com.muscatlab.memberservice.application.port.out.LoadMemberPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayName("MemberQueryService 구현체 테스트")
@ExtendWith({MockitoExtension.class})
class MemberQueryServiceTest {
    @InjectMocks
    private MemberQueryService memberQueryService;

    @Mock
    private LoadMemberPort loadMemberPort;

    private Member member;

    @BeforeEach
    void setUp() {
        String email = "email";
        String name = "name";
        String password = "password";
        member = Member.builder()
                .email(email)
                .name(name)
                .password(password)
                .build();
    }

    @Nested
    @DisplayName("loadMemberById 메서드 테스트")
    class LoadMemberById {
        @Test
        @DisplayName("should be return member")
        void mustBeReturnMember() {
            // given
            when(loadMemberPort.loadMemberBy(member.getId()))
                    .thenReturn(Optional.ofNullable(member));
            // when
            Member result = memberQueryService.loadMemberBy(member.getId());
            // then
            assertNotNull(result);
        }

        @Test
        @DisplayName("should be throw Member Not Found exception")
        void mustBeThrowMemberNotFoundException() {
            // given
            when(loadMemberPort.loadMemberBy(member.getId()))
                    .thenReturn(Optional.empty());

            // when
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                memberQueryService.loadMemberBy(member.getId());
            });

            // then
            assertNotNull(exception);
            assertEquals(exception.getMessage(), "Member not found");
        }
    }

    @Nested
    @DisplayName("loadMemberByEmail 메서드 테스트")
    class LoadMemberByEmail {
        @Test
        @DisplayName("should be return member")
        void mustBeReturnMember() {
            // given
            when(loadMemberPort.loadMemberBy(member.getEmail()))
                    .thenReturn(Optional.ofNullable(member));
            // when
            Member result = memberQueryService.loadMemberBy(member.getEmail());
            // then
            assertNotNull(result);
        }

        @Test
        @DisplayName("should be throw Member Not Found exception")
        void mustBeThrowMemberNotFoundException() {
            // given
            when(loadMemberPort.loadMemberBy(member.getEmail()))
                    .thenReturn(Optional.empty());

            // when
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                memberQueryService.loadMemberBy(member.getEmail());
            });

            // then
            assertNotNull(exception);
            assertEquals(exception.getMessage(), "Member not found");
        }
    }
}