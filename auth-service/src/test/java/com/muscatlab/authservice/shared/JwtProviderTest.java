package com.muscatlab.authservice.shared;

import com.muscatlab.authservice.config.JwtPropertiesConfig;
import com.muscatlab.authservice.config.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.UUID;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@DisplayName("JwtProvider 테스트")
@Import({TestConfig.class})
@ExtendWith({MockitoExtension.class})
class JwtProviderTest {

    @InjectMocks
    private JwtProvider jwtProvider;

    @Spy
    private JwtPropertiesConfig jwtProperties;

    private UUID memberId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(jwtProperties, "secretKey", "plzgivemesomepizzaandcoffee");
        ReflectionTestUtils.setField(jwtProperties, "tokenExpirationInHours", 24L);
        jwtProvider.init();
    }

    @Test
    @DisplayName("should be return access token with member id subject")
    void createAccessToken() {
        // when
        String accessToken = jwtProvider.createAccessToken(memberId);

        // then
        assertNotNull(accessToken);
        String subject = jwtProvider.extractSubject(accessToken);
        assertEquals(memberId.toString(), subject);
    }

    @Test
    @DisplayName("should be return refresh token with member id subject")
    void createRefreshToken() {
        // when
        String refreshToken = jwtProvider.createRefreshToken(memberId);

        // then
        assertNotNull(refreshToken);
        String subject = jwtProvider.extractSubject(refreshToken);
        assertEquals(memberId.toString(), subject);
    }

    @Test
    @DisplayName("should be return member id subject")
    void extractSubject() {
        // given
        String accessToken = jwtProvider.createAccessToken(memberId);

        // when
        String subject = jwtProvider.extractSubject(accessToken);

        // then
        assertEquals(memberId.toString(), subject);
    }

    @Test
    @DisplayName("should be throw expired token exception")
    void throwExpiredJwtException() {
        // given
        when(jwtProperties.getAccessTokenExpirationInHours()).thenReturn(0L);
        String accessToken = jwtProvider.createAccessToken(memberId);

        // when
        RuntimeException exception = assertThrows(RuntimeException.class, () -> jwtProvider.extractSubject(accessToken));

        // then
        assertEquals("Expired Token", exception.getMessage());
    }

    @Test
    @DisplayName("should be throw invalid token exception")
    void throwInvalidTokenException() {
        // given
        String invalidJwt = "invalidJwt";

        // when
        RuntimeException exception = assertThrows(RuntimeException.class, () -> jwtProvider.extractSubject(invalidJwt));

        // then
        assertEquals("Invalid Token", exception.getMessage());
    }
}