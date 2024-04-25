package com.kimtaeo.thecommercetoyproject.domain.member.service;

import com.kimtaeo.thecommercetoyproject.domain.member.enums.SortType;
import com.kimtaeo.thecommercetoyproject.domain.member.presentation.dto.request.SignUpRequest;
import com.kimtaeo.thecommercetoyproject.domain.member.presentation.dto.request.UpdateMemberRequest;
import com.kimtaeo.thecommercetoyproject.domain.member.presentation.dto.response.MemberResponse;
import com.kimtaeo.thecommercetoyproject.domain.member.presentation.dto.response.PagedMembersResponse;

public interface MemberService {
    void signUp(SignUpRequest signUpRequest);
    PagedMembersResponse queryMembers(Integer page, Integer pageSize, SortType sortType);
    MemberResponse updateMember(String memberId, UpdateMemberRequest updateMemberRequest);
}
