package com.muscatlab.authservice.shared;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("HttpResponse 테스트")
class HttpResponseTest {

    @Test
    @DisplayName("should be return success response")
    void ok() {
        // when
        ResponseEntity<String> result = HttpResponse.ok("created");

        // then
        assertTrue(result.getStatusCode().is2xxSuccessful());
        assertNotNull(result.getBody());
        assertTrue(Objects.requireNonNull(result.getBody()).contains("created"));
    }

    @Test
    @DisplayName("should be return created response")
    void created() {
        // when
        ResponseEntity<String> result = HttpResponse.created("created");

        // then
        assertTrue(result.getStatusCode().is2xxSuccessful());
        assertNotNull(result.getBody());
        assertTrue(Objects.requireNonNull(result.getBody()).contains("created"));
    }

    @Test
    @DisplayName("should be return no content response")
    void noContent() {
        // when
        ResponseEntity<String> result = HttpResponse.noContent();

        // then
        assertTrue(result.getStatusCode().is2xxSuccessful());
    }
}