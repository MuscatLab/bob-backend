package com.muscatlab.memberservice.adapter.in.web;

import com.muscatlab.memberservice.adapter.in.web.response.BooleanResponse;
import com.muscatlab.memberservice.adapter.in.web.response.MemberResponse;
import com.muscatlab.memberservice.application.domain.model.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseMapper {

    static ResponseEntity<BooleanResponse> mapToBooleanResponse(boolean result, HttpStatus status) {
        BooleanResponse response = BooleanResponse.builder()
                .result(result)
                .build();
        return new ResponseEntity<>(response, status);
    }

    static ResponseEntity<MemberResponse> mapToMemberResponse(Member member, HttpStatus status) {
        MemberResponse response = new MemberResponse(
                member.getId(),
                member.getEmail(),
                member.getName()
        );
        return new ResponseEntity<>(response, status);
    }
}
