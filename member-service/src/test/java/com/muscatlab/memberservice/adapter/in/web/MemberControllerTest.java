package com.muscatlab.memberservice.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.muscatlab.memberservice.adapter.in.web.request.SignInRequest;
import com.muscatlab.memberservice.adapter.in.web.request.SignUpRequest;
import com.muscatlab.memberservice.application.domain.model.Member;
import com.muscatlab.memberservice.application.port.in.MemberCommandUseCase;
import com.muscatlab.memberservice.application.port.in.MemberQueryUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@ExtendWith({MockitoExtension.class})
class MemberControllerTest {
    private MockMvc mockMvc;

    private ObjectMapper mapper;

    @MockBean
    private MemberCommandUseCase memberCommandUseCase;

    @MockBean
    private MemberQueryUseCase memberQueryUseCase;

    private Member member;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext) {
        member = Member.builder()
                .email("test@dev.com")
                .name("name")
                .password("password")
                .build();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.mapper = new ObjectMapper();
    }

    @Test
    @DisplayName("should be return created member response")
    void createMember() throws Exception {
        SignUpRequest requestBody = new SignUpRequest("test@dev.com", "name", "password");
        when(memberCommandUseCase.createMember(requestBody.toCommand())).thenReturn(member);
        mockMvc.perform(post("/members/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(requestBody)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(member.getId().toString()))
                .andExpect(jsonPath("$.email").value(member.getEmail()))
                .andExpect(jsonPath("$.name").value(member.getName()));
    }

    @Test
    @DisplayName("should be return sign in success")
    void signIn() throws Exception {
        SignInRequest requestBody = new SignInRequest("test@dev.com", "password");
        when(memberCommandUseCase.validatePassword(requestBody.toCommand())).thenReturn(true);
        mockMvc.perform(post("/members/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(requestBody)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(true));
    }

    @Test
    @DisplayName("should be return sign in fail")
    void signInFail() throws Exception {
        SignInRequest requestBody = new SignInRequest("test@dev.com", "password");
        when(memberCommandUseCase.validatePassword(requestBody.toCommand())).thenReturn(false);
        mockMvc.perform(post("/members/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(requestBody)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(false));
    }

    @Test
    @DisplayName("should be find member")
    void getMemberById() throws Exception {
        when(memberQueryUseCase.loadMemberBy(member.getId())).thenReturn(member);
        mockMvc.perform(get("/members/{memberId}", member.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(member.getId().toString()))
                .andExpect(jsonPath("$.email").value(member.getEmail()))
                .andExpect(jsonPath("$.name").value(member.getName()));
    }
}