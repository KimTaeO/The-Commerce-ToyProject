package com.kimtaeo.thecommercetoyproject.domain.member.service;

import com.kimtaeo.thecommercetoyproject.domain.member.presentation.dto.request.SignUpRequest;

public interface MemberService {
    void signUp(SignUpRequest signUpRequest);
}
