package com.muscatlab.authservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.muscatlab.authservice.dto.TokenResponse;
import com.muscatlab.authservice.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@ExtendWith({MockitoExtension.class})
class AuthControllerTest {
    private MockMvc mockMvc;
    @MockBean
    private AuthService authService;
    private ObjectMapper mapper;

    private UUID memberId;

    private TokenResponse tokenResponse;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext) {
        memberId = UUID.randomUUID();
        tokenResponse = TokenResponse.builder()
                .accessToken("accessToken")
                .refreshToken("refreshToken")
                .build();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.mapper = new ObjectMapper();
    }

    @Test
    @DisplayName("should be return ok response")
    void healthCheck() throws Exception {
        mockMvc.perform(get("/auth")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("should be return created response when create token")
    void createToken() throws Exception {
        when(authService.createToken(memberId)).thenReturn(tokenResponse);
        mockMvc.perform(post("/auth/{memberId}", memberId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.access_token").value("accessToken"))
                .andExpect(jsonPath("$.refresh_token").value("refreshToken"));
    }

    @Test
    @DisplayName("should be return crested response when refresh token")
    void refreshToken() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(authService.refreshToken(memberId, request, response)).thenReturn(tokenResponse);
        mockMvc.perform(post("/auth/refresh/{memberId}", memberId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}