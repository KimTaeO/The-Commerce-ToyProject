package com.kimtaeo.thecommercetoyproject.domain.member.service.impl;

import com.kimtaeo.thecommercetoyproject.domain.member.entity.Member;
import com.kimtaeo.thecommercetoyproject.domain.member.enums.SortType;
import com.kimtaeo.thecommercetoyproject.domain.member.presentation.dto.response.MemberResponse;
import com.kimtaeo.thecommercetoyproject.domain.member.presentation.dto.response.PagedMembersResponse;
import com.kimtaeo.thecommercetoyproject.domain.member.repository.MemberRepository;
import com.kimtaeo.thecommercetoyproject.domain.member.service.QueryMembersService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QueryMembersServiceImpl implements QueryMembersService {
    private final MemberRepository memberRepository;

    @Override
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
                        .nickname(member.getNickname())
                        .name(member.getName())
                        .phoneNumber(member.getPhoneNumber())
                        .build()
        );

        return new PagedMembersResponse(pagedResponse);
    }
}
