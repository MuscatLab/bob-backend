package com.muscatlab.memberservice.adapter.out.persistence.repository.impl;

import com.muscatlab.memberservice.adapter.out.persistence.repository.MemberRepository;
import com.muscatlab.memberservice.application.domain.model.Member;
import com.muscatlab.memberservice.config.QueryDslConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("MemberRepositoryCustom 구현체 테스트")
@Import({QueryDslConfig.class})
class MemberRepositoryCustomImplTest {
    @Autowired
    private MemberRepository memberRepository;

    private Member member;

    @BeforeEach
    void setUp() {
        Member member = Member.builder()
                .name("name")
                .email("email")
                .password("password")
                .build();
        this.member = memberRepository.save(member);
    }

    @Nested
    @DisplayName("findByEmail 메서드 테스트")
    class FindByEmail {
        @Test
        @DisplayName("이메일로 멤버를 찾는다.")
        void shouldBeReturnMember() {
            String email = member.getEmail();
            Member member = memberRepository.findByEmail(email)
                    .orElseThrow(() -> new IllegalArgumentException("Member not found"));

            assertNotNull(member);
            assertEquals(email, member.getEmail());
            assertEquals(Member.class, member.getClass());
        }

        @Test
        @DisplayName("이메일이 없으면 빈 Optional을 반환한다.")
        void shouldBeReturnEmptyOptional() {
            String email = "notFoundEmail";
            Optional<Member> member = memberRepository.findByEmail(email);

            assertTrue(member.isEmpty());
        }
    }
}