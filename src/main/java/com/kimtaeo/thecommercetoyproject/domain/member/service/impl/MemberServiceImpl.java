package com.kimtaeo.thecommercetoyproject.domain.member.service.impl;

import com.kimtaeo.thecommercetoyproject.domain.member.entity.Member;
import com.kimtaeo.thecommercetoyproject.domain.member.enums.SortType;
import com.kimtaeo.thecommercetoyproject.domain.member.exception.AlreadyExistEmailException;
import com.kimtaeo.thecommercetoyproject.domain.member.exception.AlreadyExistPhoneNumberException;
import com.kimtaeo.thecommercetoyproject.domain.member.presentation.dto.request.SignUpRequest;
import com.kimtaeo.thecommercetoyproject.domain.member.presentation.dto.response.MemberResponse;
import com.kimtaeo.thecommercetoyproject.domain.member.presentation.dto.response.PagedMembersResponse;
import com.kimtaeo.thecommercetoyproject.domain.member.repository.MemberRepository;
import com.kimtaeo.thecommercetoyproject.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    @Transactional(readOnly = true)
    public PagedMembersResponse queryMembers(Integer page, Integer pageSize, SortType sortType) {
        Sort sort = Sort.by(Sort.Order.asc(sortType.getStr()));

        Pageable pageable = PageRequest.of(page, pageSize, sort);

        Page<Member> pagedMembers = memberRepository.findAll(pageable);

        Page<MemberResponse> pagedResponse = pagedMembers.map(
                member -> MemberResponse.builder()
                        .id(member.getId())
                        .memberId(member.getMemberId())
                        .email(member.getEmail())
                        .password(member.getPassword())
                        .name(member.getName())
                        .phoneNumber(member.getPhoneNumber())
                        .build()
        );

        return new PagedMembersResponse(pagedResponse);
    }
}
