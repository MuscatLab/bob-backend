package com.muscatlab.authservice.service;

import com.muscatlab.authservice.dto.TokenResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.UUID;

public interface AuthService {
    TokenResponse createToken(UUID memberId);

    TokenResponse refreshToken(UUID memberId, HttpServletRequest request, HttpServletResponse response);
}
