package com.muscatlab.memberservice.adapter.in.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("ResponseMapper 테스트")
class ResponseMapperTest {
    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    @DisplayName("mapToBooleanResponse 테스트")
    void mapToBooleanResponse(boolean value) {
        // given
        ResponseMapper responseMapper = new ResponseMapper();
        for (HttpStatus httpStatus : HttpStatus.values()) {
            // when
            var result = responseMapper.mapToBooleanResponse(value, httpStatus);
            // then
            assertEquals(result.getStatusCode(), httpStatus);
            assertEquals(result.getBody(), value);
        }
    }
}