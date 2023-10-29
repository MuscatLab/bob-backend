package com.muscatlab.memberservice.adapter.out.persistence;

import com.muscatlab.memberservice.adapter.out.persistence.repository.MemberRepository;
import com.muscatlab.memberservice.application.domain.model.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class MemberPersistenceAdapterTest {
    @InjectMocks
    private MemberPersistenceAdapter memberPersistenceAdapter;
    @Mock
    private MemberRepository memberRepository;

    private Member member;

    @BeforeEach
    void setUp() {
        member = Member.builder()
                .name("name")
                .email("email")
                .password("password")
                .build();
    }

    @Nested
    @DisplayName("loadMemberByEmail method test")
    class LoadMemberByEmail {
        @Test
        @DisplayName("loadMemberByEmail should return member")
        void loadMemberBy() {
            String email = "email";
            when(memberRepository.findByEmail(email))
                    .thenReturn(Optional.ofNullable(member));
            Optional<Member> member = memberPersistenceAdapter.loadMemberBy(email);
            assertTrue(member.isPresent());
        }

        @Test
        @DisplayName("loadMemberByEmail should return empty")
        void shouldBeReturnEmpty() {
            String email = "notFoundEmail";
            when(memberRepository.findByEmail(email))
                    .thenReturn(Optional.empty());
            Optional<Member> member = memberPersistenceAdapter.loadMemberBy(email);
            assertTrue(member.isEmpty());
        }
    }

    @Nested
    @DisplayName("loadMemberById method test")
    class LoadMemberById {
        @Test
        @DisplayName("loadMemberById should return member")
        void loadMemberBy() {
            UUID id = UUID.randomUUID();
            when(memberRepository.findById(id))
                    .thenReturn(Optional.ofNullable(member));
            Optional<Member> member = memberPersistenceAdapter.loadMemberBy(id);
            assertTrue(member.isPresent());
        }

        @Test
        @DisplayName("loadMemberBy() should return empty")
        void shouldBeReturnEmpty() {
            UUID id = UUID.randomUUID();
            when(memberRepository.findById(id))
                    .thenReturn(Optional.empty());
            Optional<Member> member = memberPersistenceAdapter.loadMemberBy(id);
            assertTrue(member.isEmpty());
        }
    }

    @Nested
    @DisplayName("createMember method test")
    class CreateMember {
        @Test
        @DisplayName("createMember should return member")
        void shouldBeReturnMember() {
            when(memberRepository.save(member))
                    .thenReturn(member);
            Member result = memberPersistenceAdapter.createMember(member);
            assertNotNull(result);
        }
    }
}