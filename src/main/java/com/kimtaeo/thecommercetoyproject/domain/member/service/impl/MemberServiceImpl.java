package com.kimtaeo.thecommercetoyproject.domain.member.service.impl;

import com.kimtaeo.thecommercetoyproject.domain.member.entity.Member;
import com.kimtaeo.thecommercetoyproject.domain.member.exception.AlreadyExistEmailException;
import com.kimtaeo.thecommercetoyproject.domain.member.exception.AlreadyExistPhoneNumberException;
import com.kimtaeo.thecommercetoyproject.domain.member.presentation.dto.request.SignUpRequest;
import com.kimtaeo.thecommercetoyproject.domain.member.repository.MemberRepository;
import com.kimtaeo.thecommercetoyproject.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void signUp(SignUpRequest signUpRequest) {
        if(memberRepository.existsByMemberId(signUpRequest.getMemberId()))
            throw new AlreadyExistPhoneNumberException(String.format("이미 등록된 아이디 입니다. [ memberId = %s ]", signUpRequest.getMemberId()));

        if(memberRepository.existsByEmail(signUpRequest.getEmail()))
            throw new AlreadyExistEmailException(String.format("이미 등록된 이메일 입니다. [ email = %s ]", signUpRequest.getEmail()));

        if(memberRepository.existsByPhoneNumber(signUpRequest.getPhoneNumber()))
            throw new AlreadyExistPhoneNumberException(String.format("이미 등록된 전화번호 입니다. [ phoneNumber = %s ]", signUpRequest.getPhoneNumber()));

        Member member = Member.builder()
                .id(UUID.randomUUID())
                .memberId(signUpRequest.getMemberId())
                .email(signUpRequest.getEmail())
                .password(signUpRequest.getPassword())
                .name(signUpRequest.getName())
                .phoneNumber(signUpRequest.getPhoneNumber())
                .build();

        memberRepository.save(member);
    }
}
