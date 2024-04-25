package com.kimtaeo.thecommercetoyproject.domain.member.service;

import com.kimtaeo.thecommercetoyproject.domain.member.enums.SortType;
import com.kimtaeo.thecommercetoyproject.domain.member.presentation.dto.response.PagedMembersResponse;

public interface QueryMembersService {
    PagedMembersResponse queryMembers(Integer page, Integer pageSize, SortType sortType);
}
