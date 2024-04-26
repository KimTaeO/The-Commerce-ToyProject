package com.kimtaeo.thecommercetoyproject.domain.member.service.impl;

import com.kimtaeo.thecommercetoyproject.domain.member.entity.Member;
import com.kimtaeo.thecommercetoyproject.domain.member.enums.SortType;
import com.kimtaeo.thecommercetoyproject.domain.member.presentation.dto.response.MemberResponse;
import com.kimtaeo.thecommercetoyproject.domain.member.presentation.dto.response.PagedMembersResponse;
import com.kimtaeo.thecommercetoyproject.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Given(page, pageSize, sort 가 주어지면)")
public class QueryMembersServiceImplTest {
    @InjectMocks
    private QueryMembersServiceImpl queryMembersServiceImpl;
    @Mock
    private MemberRepository memberRepository;
    Integer page = 0;
    Integer pageSize = 1;
    SortType sortType = SortType.NAME;

    UUID id = UUID.randomUUID();
    String memberId = "asdf1234";
    String email = "asdf1234@gmail.com";
    String password = "qwer1234!";
    String nickname = "나는야홍길동";
    String name = "홍길동";
    String phoneNumber = "01012345678";
    LocalDateTime createdAt = LocalDateTime.MAX;

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
    MemberResponse memberResponse = MemberResponse.builder()
            .id(id)
            .memberId(memberId)
            .email(email)
            .password(password)
            .nickname(nickname)
            .name(name)
            .phoneNumber(phoneNumber)
            .build();
    PagedMembersResponse pagedMembersResponse = new PagedMembersResponse(
            new PageImpl<>(Arrays.asList(memberResponse))
    );

    @Test
    @DisplayName("When(members 요청 시)")
    void queryMembersTest() {
        given(memberRepository.findAll(any(Pageable.class))).willReturn(new PageImpl<>(Arrays.asList(member)));

        PagedMembersResponse result = queryMembersServiceImpl.queryMembers(page, pageSize, sortType);


        result.getMembers().getContent();

        // Then(expected 의 요소와 actual의 요소가 같아야 한다)
        MemberResponse expected = pagedMembersResponse.getMembers().getContent().get(0);
        MemberResponse actual = result.getMembers().getContent().get(0);

        assertAll(
                () -> assertEquals(expected.getId(), actual.getId()),
                () -> assertEquals(expected.getMemberId(), actual.getMemberId()),
                () -> assertEquals(expected.getEmail(), actual.getEmail()),
                () -> assertEquals(expected.getPassword(), actual.getPassword()),
                () -> assertEquals(expected.getNickname(), actual.getNickname()),
                () -> assertEquals(expected.getName(), actual.getName()),
                () -> assertEquals(expected.getPhoneNumber(), actual.getPhoneNumber())
        );
    }
}
