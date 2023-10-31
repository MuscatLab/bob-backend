package com.muscatlab.memberservice.adapter.in.web;

import com.muscatlab.memberservice.adapter.in.web.request.SignInRequest;
import com.muscatlab.memberservice.adapter.in.web.request.SignUpRequest;
import com.muscatlab.memberservice.adapter.in.web.response.BooleanResponse;
import com.muscatlab.memberservice.adapter.in.web.response.MemberResponse;
import com.muscatlab.memberservice.application.domain.model.Member;
import com.muscatlab.memberservice.application.port.in.MemberCommandUseCase;
import com.muscatlab.memberservice.application.port.in.MemberQueryUseCase;
import com.muscatlab.memberservice.common.WebAdapter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@WebAdapter
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberCommandUseCase memberCommandUseCase;
    private final MemberQueryUseCase memberQueryUseCase;

    @PostMapping("/sign-up")
    public ResponseEntity<MemberResponse> signUp(@RequestBody @Valid SignUpRequest body) {
        Member member = memberCommandUseCase.createMember(body.toCommand());
        return ResponseMapper.mapToMemberResponse(member, HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<BooleanResponse> signIn(@RequestBody @Valid SignInRequest body) {
        boolean result = memberCommandUseCase.validatePassword(body.toCommand());
        return ResponseMapper.mapToBooleanResponse(result, HttpStatus.OK);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponse> getMemberById(@PathVariable UUID memberId) {
        Member member = memberQueryUseCase.loadMemberBy(memberId);
        return ResponseMapper.mapToMemberResponse(member, HttpStatus.OK);
    }
}
