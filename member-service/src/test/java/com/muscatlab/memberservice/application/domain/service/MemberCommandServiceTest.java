package com.muscatlab.memberservice.application.domain.service;

import com.muscatlab.memberservice.application.domain.model.Member;
import com.muscatlab.memberservice.application.port.in.CreateMemberCommand;
import com.muscatlab.memberservice.application.port.in.ValidatePasswordCommand;
import com.muscatlab.memberservice.application.port.out.CreateMemberPort;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("MemberCommandService 구현체 테스트")
@ExtendWith({MockitoExtension.class})
class MemberCommandServiceTest {

    @InjectMocks
    private MemberCommandService memberCommandService;

    @Mock
    private LoadMemberPort loadMemberPort;

    @Mock
    private CreateMemberPort createMemberPort;

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
    @DisplayName("createMember 메소드 테스트")
    class CreateMember {
        @Test
        @DisplayName("should be created member")
        void mustBeReturnMember() {
            // given
            CreateMemberCommand command = new CreateMemberCommand(member.getEmail(), member.getName(), member.getPassword());
            when(createMemberPort.createMember(any(Member.class))).thenReturn(member);

            // when
            Member result = memberCommandService.createMember(command);

            // then
            assertNotNull(result);
            assertEquals(result.getEmail(), member.getEmail());
            assertEquals(result.getName(), member.getName());
        }

        @Test
        @DisplayName("Member is already exsits")
        void mustBeThrowMemberExistsException() {
            // given
            CreateMemberCommand command = new CreateMemberCommand(member.getEmail(), member.getName(), member.getPassword());
            when(loadMemberPort.loadMemberBy(any(String.class))).thenReturn(java.util.Optional.ofNullable(member));

            // when
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> memberCommandService.createMember(command));

            // then
            assertEquals(exception.getMessage(), "Member already exists");
        }
    }

    @Nested
    @DisplayName("validatePassword 메소드 테스트")
    class ValidatePassword {
        @Test
        @DisplayName("should be validate success password")
        void mustBeReturnTrue() {
            // given
            String password = "password";
            String email = "email";
            ValidatePasswordCommand command = new ValidatePasswordCommand(email, password);
            when(loadMemberPort.loadMemberBy(email)).thenReturn(Optional.ofNullable(member));

            // when
            boolean result = memberCommandService.validatePassword(command);

            // then
            assertTrue(result);
        }

        @Test
        @DisplayName("should be validate fail password")
        void mustBeReturnFalse() {
            // given
            String password = "not validate password";
            String email = "email";
            ValidatePasswordCommand command = new ValidatePasswordCommand(email, password);
            when(loadMemberPort.loadMemberBy(email)).thenReturn(Optional.ofNullable(member));

            // when
            boolean result = memberCommandService.validatePassword(command);

            // then
            assertFalse(result);
        }

        @Test
        @DisplayName("should be not found member")
        void mustBeThrowMemberNotFoundException() {
            // given
            String password = "password";
            String email = "email";
            ValidatePasswordCommand command = new ValidatePasswordCommand(email, password);
            when(loadMemberPort.loadMemberBy(email)).thenReturn(Optional.empty());

            // when
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> memberCommandService.validatePassword(command));

            // then
            assertEquals(exception.getMessage(), "Member not found");
        }
    }
}