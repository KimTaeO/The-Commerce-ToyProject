package com.kimtaeo.thecommercetoyproject.domain.member.service.impl;

import com.kimtaeo.thecommercetoyproject.domain.member.entity.Member;
import com.kimtaeo.thecommercetoyproject.domain.member.exception.MemberNotFoundException;
import com.kimtaeo.thecommercetoyproject.domain.member.presentation.dto.request.UpdateMemberRequest;
import com.kimtaeo.thecommercetoyproject.domain.member.presentation.dto.response.MemberResponse;
import com.kimtaeo.thecommercetoyproject.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Given(memberId와 updateMemberRequest 가 주어지면)")
public class UpdateMemberServiceImplTest {
    @InjectMocks
    private UpdateMemberServiceImpl updateMemberServiceImpl;
    @Mock
    private MemberRepository memberRepository;
    UUID id = UUID.randomUUID();
    String invalidMemberId = "InvalidId";
    String memberId = "asdf1234";
    String email = "asdf1234@gmail.com";
    String password = "qwer1234!";
    String nickname = "나는야홍길동";
    String name = "홍길동";
    String phoneNumber = "01012345678";
    LocalDateTime createdAt = LocalDateTime.MAX;

    String updatedEmail = "modified@gmail.com";
    String updatedPassword = "updatedPassword";
    String updatedNickname = "updatedNickname";
    String updatedName = "updatedName";
    String updatedPhoneNumber = "01099999999";

    Member member = Member.builder()
            .id(id)
            .memberId(memberId)
            .email(email)
            .password(password)
            .nickname(nickname)
            .name(name)
            .phoneNumber(phoneNumber)
            .createdAt(createdAt)
            .build();

    Member updatedMember = Member.builder()
            .id(id)
            .memberId(memberId)
            .email(updatedEmail)
            .password(updatedPassword)
            .nickname(updatedNickname)
            .name(updatedName)
            .phoneNumber(phoneNumber)
            .createdAt(createdAt)
            .build();

    UpdateMemberRequest updateMemberRequest = UpdateMemberRequest.builder()
            .email(updatedEmail)
            .password(updatedPassword)
            .nickname(updatedNickname)
            .name(updatedName)
            .phoneNumber(updatedPhoneNumber)
            .build();

    MemberResponse memberResponse = MemberResponse.builder()
            .id(updatedMember.getId())
            .memberId(updatedMember.getMemberId())
            .email(updatedMember.getEmail())
            .password(updatedMember.getPassword())
            .nickname(updatedMember.getNickname())
            .name(updatedMember.getName())
            .phoneNumber(updatedMember.getPhoneNumber())
            .build();

    @Test
    @DisplayName("When(Member 수정 시)")
    void updateMemberTest() {
        given(memberRepository.findByMemberId(any())).willReturn(Optional.ofNullable(member));
        given(memberRepository.save(any())).willReturn(updatedMember);

        MemberResponse result = updateMemberServiceImpl.updateMember(memberId, updateMemberRequest);

        assertAll(
                () -> assertEquals(memberResponse.getId(), result.getId()),
                () -> assertEquals(memberResponse.getMemberId(), result.getMemberId()),
                () -> assertEquals(memberResponse.getEmail(), result.getEmail()),
                () -> assertEquals(memberResponse.getPassword(), result.getPassword()),
                () -> assertEquals(memberResponse.getNickname(), result.getNickname()),
                () -> assertEquals(memberResponse.getName(), result.getName()),
                () -> assertEquals(memberResponse.getPhoneNumber(), result.getPhoneNumber())
        );
    }

    @Test
    @DisplayName("When(유효하지 않은 memberId 라면)")
    void validateInvalidMemberId() {
        given(memberRepository.findByMemberId(invalidMemberId)).willReturn(Optional.empty());

        // Then(MemberNotFoundException 이 발생해야 한다)
        assertThrows(MemberNotFoundException.class, () -> updateMemberServiceImpl.updateMember(invalidMemberId, updateMemberRequest));
    }
}
