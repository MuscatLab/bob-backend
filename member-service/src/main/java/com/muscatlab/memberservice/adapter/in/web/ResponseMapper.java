package com.muscatlab.memberservice.adapter.in.web;

import com.muscatlab.memberservice.adapter.in.web.response.MemberResponse;
import com.muscatlab.memberservice.application.domain.model.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseMapper {

    ResponseEntity<Boolean> mapToBooleanResponse(boolean result, HttpStatus status) {
        return ResponseEntity.status(status).body(result);
    }

    ResponseEntity<MemberResponse> mapToMemberResponse(Member member, HttpStatus status) {
        MemberResponse response = new MemberResponse(
                member.getId(),
                member.getEmail(),
                member.getName()
        );
        return ResponseEntity.status(status).body(response);
    }
}
