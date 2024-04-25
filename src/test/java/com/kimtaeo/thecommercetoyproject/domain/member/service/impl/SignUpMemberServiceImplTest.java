package com.kimtaeo.thecommercetoyproject.domain.member.service.impl;

import com.kimtaeo.thecommercetoyproject.domain.member.entity.Member;
import com.kimtaeo.thecommercetoyproject.domain.member.exception.AlreadyExistEmailException;
import com.kimtaeo.thecommercetoyproject.domain.member.exception.AlreadyExistMemberIdException;
import com.kimtaeo.thecommercetoyproject.domain.member.exception.AlreadyExistPhoneNumberException;
import com.kimtaeo.thecommercetoyproject.domain.member.presentation.dto.request.SignUpRequest;
import com.kimtaeo.thecommercetoyproject.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Given(signUpRequest 가 주어지면)")
public class SignUpMemberServiceImplTest {
    @InjectMocks
    private SignUpMemberServiceImpl memberService;
    @Mock
    private MemberRepository memberRepository;

    UUID id = UUID.randomUUID();
    String memberId = "asdf1234";
    String email = "asdf1234@gmail.com";
    String password = "qwer1234!";
    String name = "홍길동";
    String phoneNumber = "01012345678";
    LocalDateTime createdAt = LocalDateTime.MAX;

    SignUpRequest signUpRequest = SignUpRequest.builder()
            .memberId(memberId)
            .email(email)
            .password(password)
            .name(name)
            .phoneNumber(phoneNumber)
            .build();

    Member member = Member.builder()
            .id(id)
            .memberId(memberId)
            .email(email)
            .password(password)
            .name(name)
            .phoneNumber(phoneNumber)
            .createdAt(createdAt)
            .build();

    @Test
    @DisplayName("When(Member 추가 시)")
    void signUpTest() {
        given(memberRepository.existsByMemberId(any())).willReturn(false);
        given(memberRepository.existsByEmail(any())).willReturn(false);
        given(memberRepository.existsByPhoneNumber(any())).willReturn(false);
        given(memberRepository.save(any())).willReturn(member);

        assertDoesNotThrow(() -> memberService.signUp(signUpRequest));

        // Then(Member가 저장이 되어야 한다)
        verify(memberRepository, times(1)).save(any(Member.class));
    }

    @Test
    @DisplayName("When(이미 존재하는 memberId로 signUp 요청 시)")
    void validExistsByMemberId() {

        given(memberRepository.existsByMemberId(any())).willReturn(true);

        // Then(AlreadyExistMemberIdException 이 발생해야 한다)
        assertThrows(AlreadyExistMemberIdException.class, () -> memberService.signUp(signUpRequest));
    }
}
