package com.muscatlab.memberservice.adapter.in.web;

import com.muscatlab.memberservice.adapter.in.web.response.BooleanResponse;
import com.muscatlab.memberservice.application.domain.model.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
            BooleanResponse response = BooleanResponse.builder()
                    .result(value)
                    .build();
            assertEquals(result.getStatusCode(), httpStatus);
            assertEquals(result.getBody(), response);
        }
    }

    @Test
    @DisplayName("mapToMemberResponse 테스트")
    void mapToMemberResponse() {
        // given
        ResponseMapper responseMapper = new ResponseMapper();
        Member member = Member.builder()
                .name("name")
                .email("email")
                .password("password")
                .build();
        for (HttpStatus httpStatus : HttpStatus.values()) {
            // when
            var result = responseMapper.mapToMemberResponse(member, httpStatus);
            // then
            assertEquals(result.getStatusCode(), httpStatus);
            assertEquals(result.getBody().email(), member.getEmail());
            assertEquals(result.getBody().name(), member.getName());
        }
    }
}