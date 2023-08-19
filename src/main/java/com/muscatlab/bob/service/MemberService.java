package com.muscatlab.bob.service;

import com.muscatlab.bob.dto.member.MemberOutput;
import com.muscatlab.bob.dto.member.SignInInput;
import com.muscatlab.bob.dto.member.SignUpInput;

public interface MemberService {
    MemberOutput signUp(SignUpInput input);

    MemberOutput signIn(SignInInput input);
}
