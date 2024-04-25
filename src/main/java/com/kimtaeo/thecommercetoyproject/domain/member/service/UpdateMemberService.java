package com.kimtaeo.thecommercetoyproject.domain.member.service;

import com.kimtaeo.thecommercetoyproject.domain.member.presentation.dto.request.UpdateMemberRequest;
import com.kimtaeo.thecommercetoyproject.domain.member.presentation.dto.response.MemberResponse;

public interface UpdateMemberService {
    MemberResponse updateMember(String memberId, UpdateMemberRequest updateMemberRequest);
}
