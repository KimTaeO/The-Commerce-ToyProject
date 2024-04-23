package com.kimtaeo.thecommercetoyproject.domain.member.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class SignUpRequest {
    @NotBlank
    String memberId;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^*+=-]).{8,15}$") // 영문, 숫자, 특수기호 조합 8자리 이상
    private String password;

    @NotBlank
    private String nickname;

    @NotBlank
    private String name;

    @NotBlank
    @Pattern(regexp = "^010[0-9]{8}$") // ex) 010으로 시작, 숫자 8자리
    private String phoneNumber;

    @Email
    @NotNull
    private String email;
}
