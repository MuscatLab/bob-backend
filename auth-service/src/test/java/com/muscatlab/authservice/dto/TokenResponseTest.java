package com.muscatlab.authservice.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TokenResponse 테스트")
class TokenResponseTest {

    @Nested
    @DisplayName("생성자 테스트")
    class Constructor {

        private TokenResponse tokenResponse;

        @BeforeEach
        void setUP() {
            tokenResponse = TokenResponse.builder()
                    .accessToken("accessToken")
                    .refreshToken("refreshToken")
                    .build();
        }

        @Test
        @DisplayName("should be not null")
        void buildTokenResponse() {
            // then
            assertNotNull(tokenResponse);
            assertEquals(tokenResponse.getAccessToken(), "accessToken");
            assertEquals(tokenResponse.getRefreshToken(), "refreshToken");
        }

        @Test
        @DisplayName("should be throw NullPointerException when accessToken is null")
        void accessTokenIsNull() {
            // given
            String refreshToken = "refreshToken";

            // when
            NullPointerException exception = assertThrows(NullPointerException.class, () -> TokenResponse.builder()
                    .refreshToken(refreshToken)
                    .build());

            // then
            assertEquals(exception.getMessage(), "accessToken is marked non-null but is null");
        }

        @Test
        @DisplayName("should be throw NullPointerException when refreshToken is null")
        void refreshTokenIsNull() {
            // given
            String accessToken = "accessToken";

            // when
            NullPointerException exception = assertThrows(NullPointerException.class, () -> TokenResponse.builder()
                    .accessToken(accessToken)
                    .build());

            // then
            assertEquals(exception.getMessage(), "refreshToken is marked non-null but is null");
        }
    }

    @Nested
    @DisplayName("Getter 테스트")
    class Getter {
        private TokenResponse tokenResponse;

        private String accessToken;

        private String refreshToken;

        @BeforeEach
        void setUp() {
            accessToken = "accessToken";
            refreshToken = "refreshToken";
            tokenResponse = TokenResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        }

        @Test
        @DisplayName("should be return accessToken")
        void getAccessToken() {
            // when
            String accessToken = tokenResponse.getAccessToken();

            // then
            assertEquals(accessToken, "accessToken");
        }

        @Test
        @DisplayName("should be return refreshToken")
        void getRefreshToken() {
            // when
            String refreshToken = tokenResponse.getRefreshToken();

            // then
            assertEquals(refreshToken, "refreshToken");
        }
    }

    @Nested
    @DisplayName("Data annotation 테스트")
    class Data {
        private TokenResponse tokenResponse;

        private String accessToken;

        private String refreshToken;

        @BeforeEach
        void setUp() {
            accessToken = "accessToken";
            refreshToken = "refreshToken";
            tokenResponse = TokenResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        }

        @Test
        @DisplayName("should be return equals")
        void equalsAccessTokenAndRefreshToken() {
            // when & then
            assertTrue(tokenResponse.getAccessToken().equals("accessToken"));
            assertTrue(tokenResponse.getRefreshToken().equals("refreshToken"));
        }

        @Test
        @DisplayName("should be not null when hashCode")
        void hashCodeIsNotNull() {
            // when & then
            assertNotNull(tokenResponse.hashCode());
        }

        @Test
        @DisplayName("should be true when toString")
        void toStringIsTrue() {
            // when & then
            assertTrue(tokenResponse.toString().contains("accessToken"));
            assertTrue(tokenResponse.toString().contains("refreshToken"));
        }
    }
}