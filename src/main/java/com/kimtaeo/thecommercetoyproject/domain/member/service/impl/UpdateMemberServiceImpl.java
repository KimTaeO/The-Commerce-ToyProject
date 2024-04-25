package com.kimtaeo.thecommercetoyproject.domain.member.service.impl;

import com.kimtaeo.thecommercetoyproject.domain.member.entity.Member;
import com.kimtaeo.thecommercetoyproject.domain.member.exception.MemberNotFoundException;
import com.kimtaeo.thecommercetoyproject.domain.member.presentation.dto.request.UpdateMemberRequest;
import com.kimtaeo.thecommercetoyproject.domain.member.presentation.dto.response.MemberResponse;
import com.kimtaeo.thecommercetoyproject.domain.member.repository.MemberRepository;
import com.kimtaeo.thecommercetoyproject.domain.member.service.UpdateMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UpdateMemberServiceImpl implements UpdateMemberService {
    private final MemberRepository memberRepository;

    @Override
    public MemberResponse updateMember(String memberId, UpdateMemberRequest updateMemberRequest) {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new MemberNotFoundException(String.format("해당 회원을 찾을 수 없습니다. [ memberId = %s]", memberId)));

        Member updatedMember = Member.builder()
                .id(member.getId())
                .memberId(memberId)
                .email(updateMemberRequest.getEmail())
                .password(updateMemberRequest.getPassword())
                .nickname(updateMemberRequest.getNickname())
                .name(updateMemberRequest.getName())
                .phoneNumber(updateMemberRequest.getPhoneNumber())
                .createdAt(member.getCreatedAt())
                .build();

        Member savedMember = memberRepository.save(updatedMember);

        return MemberResponse.builder()
                .id(savedMember.getId())
                .memberId(savedMember.getMemberId())
                .email(savedMember.getEmail())
                .password(savedMember.getPassword())
                .nickname(savedMember.getNickname())
                .name(savedMember.getName())
                .phoneNumber(savedMember.getPhoneNumber())
                .build();
    }
}
