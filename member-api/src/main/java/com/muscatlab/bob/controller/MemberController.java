package com.muscatlab.bob.controller;

import com.muscatlab.bob.dto.member.MemberOutput;
import com.muscatlab.bob.dto.member.SignInInput;
import com.muscatlab.bob.dto.member.SignUpInput;
import com.muscatlab.bob.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원", description = "회원 관련 API")
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService service;

    @Operation(summary = "회원가입")
    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberOutput signUp(@RequestBody @Valid SignUpInput input) {
        return this.service.signUp(input);
    }

    @Operation(summary = "로그인")
    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public MemberOutput signIn(@RequestBody @Valid SignInInput input) {
        return this.service.signIn(input);
    }
}
