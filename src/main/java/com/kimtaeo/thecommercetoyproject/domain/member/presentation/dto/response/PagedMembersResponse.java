package com.kimtaeo.thecommercetoyproject.domain.member.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@AllArgsConstructor
public class PagedMembersResponse {
    private Page<MemberResponse> members;
}


