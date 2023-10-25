package com.muscatlab.authservice.shared;

import com.muscatlab.authservice.config.JwtPropertiesConfig;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final JwtPropertiesConfig jwtProperties;
    private String encSecretKey;

    @PostConstruct
    public void init() {
        encSecretKey = Base64.getEncoder().encodeToString(jwtProperties.getSecretKey().getBytes());
    }

    public String createAccessToken(UUID memberId) {
        return createToken(memberId.toString(), jwtProperties.getAccessTokenExpirationInHours());
    }

    public String createRefreshToken(UUID memberId) {
        return createToken(memberId.toString(), jwtProperties.getRefreshTokenExpirationInHours());
    }

    public String extractSubject(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(encSecretKey.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(removeTokenPrefix(token))
                    .getBody()
                    .getSubject();
        } catch (ExpiredJwtException e) {
            // TODO: implement custom exception
            throw new RuntimeException("Expired Token");
        } catch (MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            // TODO: implement custom exception
            throw new RuntimeException("Invalid Token");
        }
    }

    private String createToken(String memberId, long expirationHours) {
        Map<String, Object> headers = createHeader();
        Claims claims = createClaims(memberId);
        LocalDateTime now = LocalDateTime.now();

        return Jwts.builder()
                .setHeader(headers)
                .setClaims(claims)
                .setIssuedAt(Timestamp.valueOf(now))
                .setExpiration(Timestamp.valueOf(now.plusHours(expirationHours)))
                .signWith(Keys.hmacShaKeyFor(encSecretKey.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims createClaims(String memberId) {
        Claims claims = Jwts.claims().setSubject(memberId); // JWT payload에 저장되는 정보단위
        return claims;
    }

    private Map<String, Object> createHeader() {
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");
        return headers;
    }

    private String removeTokenPrefix(String token) {
        return token.replaceAll(TokenConstants.TOKEN_PREFIX_REGEX + "( )*", "");
    }

    private boolean isMatchedPrefix(String token) {
        return Pattern.matches(TokenConstants.TOKEN_PREFIX_REGEX + " .*", token);
    }
}
