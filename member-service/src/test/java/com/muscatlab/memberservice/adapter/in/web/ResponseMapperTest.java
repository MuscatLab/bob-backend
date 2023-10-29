package com.muscatlab.memberservice.adapter.in.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.HttpStatus;

@DisplayName("ResponseMapper 테스트")
class ResponseMapperTest {
    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    @EnumSource(HttpStatus.class)
    @DisplayName("mapToBooleanResponse 테스트")
    void mapToBooleanResponse(boolean value, HttpStatus status) {
        // given
        ResponseMapper responseMapper = new ResponseMapper();
        // when
        var result = responseMapper.mapToBooleanResponse(value, status);
        // then
        assert result.getStatusCode() == status;
        assert result.getBody() == value;
    }
}