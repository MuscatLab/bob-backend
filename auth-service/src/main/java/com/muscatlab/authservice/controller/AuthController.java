package com.muscatlab.authservice.controller;

import com.muscatlab.authservice.dto.TokenResponse;
import com.muscatlab.authservice.service.AuthService;
import com.muscatlab.authservice.shared.HttpResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void healthCheck() {

    }

    @PostMapping("/{memberId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TokenResponse> createToken(@PathVariable UUID memberId) {
        TokenResponse tokenResponse = authService.createToken(memberId);
        return HttpResponse.ok(tokenResponse);
    }

    @PostMapping("/refresh/{memberId}")
    public ResponseEntity<TokenResponse> refreshToken(@PathVariable UUID memberId,
                                                      HttpServletRequest request, HttpServletResponse response) {
        TokenResponse tokenResponse = authService.refreshToken(memberId, request, response);
        return HttpResponse.ok(tokenResponse);
    }

}
