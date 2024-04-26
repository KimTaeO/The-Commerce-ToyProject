package com.kimtaeo.thecommercetoyproject.domain.member.presentation;

import com.kimtaeo.thecommercetoyproject.domain.member.entity.Member;
import com.kimtaeo.thecommercetoyproject.domain.member.presentation.dto.response.MemberResponse;
import com.kimtaeo.thecommercetoyproject.domain.member.presentation.dto.response.PagedMembersResponse;
import com.kimtaeo.thecommercetoyproject.domain.member.service.QueryMembersService;
import com.kimtaeo.thecommercetoyproject.domain.member.service.SignUpMemberService;
import com.kimtaeo.thecommercetoyproject.domain.member.service.UpdateMemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs
@WebMvcTest(MemberController.class)
public class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SignUpMemberService signUpMemberService;
    @MockBean
    private QueryMembersService queryMembersService;
    @MockBean
    private UpdateMemberService updateMemberService;
    @MockBean
    private JpaMetamodelMappingContext jpaMetamodelMappingContext;

    @DisplayName("signUpMember web layer 테스트 코드입니다.")
    @Test
    void signUpMemberTest() throws Exception {
        String requestBody =
                "{\n" +
                "    \"memberId\": \"asdf1234\",\n" +
                "    \"password\": \"qwer1234!\",\n" +
                "    \"nickname\": \"나는야홍길동\",\n" +
                "    \"name\": \"홍길동\",\n" +
                "    \"phoneNumber\": \"01012345678\",\n" +
                "    \"email\": \"asdf1234@gmail.com\"\n" +
                "}";

        mockMvc.perform(post("/api/user/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(MockMvcResultHandlers.print())
                .andDo(MockMvcRestDocumentation.document("member/signUpMember",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()))
                )
                .andExpect(status().isCreated());
    }

    @DisplayName("queryMembers web layer 테스트 코드입니다.")
    @Test
    void queryMembersTest() throws Exception {
        MemberResponse memberResponse = MemberResponse.builder()
                .id(UUID.randomUUID())
                .memberId("asdf1234")
                .email("asdf1234@gmail.com")
                .password("qwer1234!")
                .nickname("나는야홍길동")
                .name("홍길동")
                .phoneNumber("01012345678")
                .build();

        PagedMembersResponse pagedMembersResponse = new PagedMembersResponse(
                new PageImpl<>(Collections.singletonList(memberResponse))
        );

        given(queryMembersService.queryMembers(any(), any(), any())).willReturn(pagedMembersResponse);

        mockMvc.perform(get("/api/user/list")
                    .param("page", "0")
                    .param("pageSize", "1")
                    .param("sort", "NAME")
                )
                .andDo(MockMvcResultHandlers.print())
                .andDo(MockMvcRestDocumentation.document("member/queryMembers",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()))
                )
                .andExpect(status().isOk());
    }

    @DisplayName("updateMember web layer 테스트 코드입니다.")
    @Test
    void updateMemberTest() throws Exception {
        UUID id = UUID.randomUUID();
        String memberId = "asdf1234";

        Member member = Member.builder()
                .id(id)
                .memberId(memberId)
                .email("asdf1234@gmail.com")
                .password("qwer1234!")
                .nickname("나는야홍길동")
                .name("홍길동")
                .phoneNumber("01012345678")
                .build();

        MemberResponse memberResponse = MemberResponse.builder()
                .id(id)
                .memberId(memberId)
                .email("updatedEmail@gmail.com")
                .password("updatedPassword1234!")
                .nickname("updatedNickname")
                .name("updatedName")
                .phoneNumber("01011111111")
                .build();

        String requestBody =
                "{\n" +
                "    \"email\": \"updatedEmail@gmail.com\",\n" +
                "    \"password\": \"updated1234!\",\n" +
                "    \"nickname\": \"updatedNickname\",\n" +
                "    \"name\": \"updatedName\",\n" +
                "    \"phoneNumber\": \"01011111111\"\n" +
                "}";

        given(updateMemberService.updateMember(any(), any())).willReturn(memberResponse);

        mockMvc.perform(patch("/api/user/{member_id}", memberId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(MockMvcResultHandlers.print())
                .andDo(MockMvcRestDocumentation.document("member/updateMember",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()))
                )
                .andExpect(status().isOk());
    }
}
