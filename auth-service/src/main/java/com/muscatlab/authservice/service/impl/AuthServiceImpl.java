package com.muscatlab.authservice.service.impl;

import com.muscatlab.authservice.dto.TokenResponse;
import com.muscatlab.authservice.service.AuthService;
import com.muscatlab.authservice.shared.JwtProvider;
import com.muscatlab.authservice.shared.TokenConstants;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtProvider jwtProvider;

    @Override
    public TokenResponse createToken(UUID memberId) {
        String accessToken = jwtProvider.createAccessToken(memberId);
        String refreshToken = jwtProvider.createRefreshToken(memberId);

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public TokenResponse refreshToken(UUID memberId, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = Optional.ofNullable(request.getCookies())
                .filter(Objects::nonNull)
                .orElseThrow(() -> new RuntimeException("No Cookie"));

        Optional<Cookie> refreshCookie = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(TokenConstants.REFRESH_TOKEN_HEADER_NAME))
                .findFirst();
        if (refreshCookie.isEmpty()) {
            throw new RuntimeException("No Refresh Token");
        }

        refreshCookie.map(Cookie::getValue)
                .map(jwtProvider::extractSubject)
                .orElseThrow(() -> new RuntimeException("Invalid Refresh Token"));

        TokenResponse tokenResponse = createToken(memberId);
        Cookie refreshTokenCookie = new Cookie(TokenConstants.REFRESH_TOKEN_HEADER_NAME, tokenResponse.getRefreshToken());
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setPath("/");
        response.addCookie(refreshTokenCookie);

        return tokenResponse;
    }
}
