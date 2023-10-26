package com.muscatlab.authservice.service.impl;

import com.muscatlab.authservice.dto.TokenResponse;
import com.muscatlab.authservice.shared.JwtProvider;
import com.muscatlab.authservice.shared.TokenConstants;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("AuthService 구현체 테스트")
@ExtendWith({MockitoExtension.class})
class AuthServiceImplTest {

    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private JwtProvider jwtProvider;

    @BeforeEach
    void setUp() {
        authService = new AuthServiceImpl(jwtProvider);
    }

    @Nested
    @DisplayName("createToken method test")
    class CreateToken {
        private UUID memberId = UUID.randomUUID();

        @Test
        @DisplayName("should be return token response")
        void createToken() {
            // given
            when(jwtProvider.createAccessToken(memberId)).thenReturn("accessToken");
            when(jwtProvider.createRefreshToken(memberId)).thenReturn("refreshToken");

            // when
            TokenResponse result = authService.createToken(memberId);

            // then
            assertNotNull(result);
        }
    }

    @Nested
    @DisplayName("refreshToken method test")
    class RefreshToken {
        private UUID memberId = UUID.randomUUID();
        private String refreshToken = "refreshToken";
        private HttpServletRequest request = mock(HttpServletRequest.class);
        private HttpServletResponse response = mock(HttpServletResponse.class);

        @Test
        @DisplayName("should be return token response")
        void refreshToken() {
            // given
            when(request.getCookies()).thenReturn(Arrays.array(new Cookie(TokenConstants.REFRESH_TOKEN_HEADER_NAME, refreshToken)));
            when(jwtProvider.extractSubject(refreshToken)).thenReturn(memberId.toString());
            when(jwtProvider.createAccessToken(memberId)).thenReturn("accessToken");
            when(jwtProvider.createRefreshToken(memberId)).thenReturn("refreshToken");

            // when
            TokenResponse result = authService.refreshToken(memberId, request, response);

            // then
            assertNotNull(result);
        }

        @Test
        @DisplayName("should be return no refresh token exception")
        void noRefreshToken() {
            // given
            when(request.getCookies()).thenReturn(Arrays.array());

            // when
            RuntimeException exception = assertThrows(RuntimeException.class, () -> authService.refreshToken(memberId, request, response));

            // then
            assertNotNull(exception);
            assertEquals("No Refresh Token", exception.getMessage());
        }
    }
}