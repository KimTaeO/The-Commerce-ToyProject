package com.kimtaeo.thecommercetoyproject.domain.member.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class MemberResponse {
    private UUID id;
    private String memberId;
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
}
