package com.kimtaeo.thecommercetoyproject.domain.member.presentation;

import com.kimtaeo.thecommercetoyproject.domain.member.service.QueryMembersService;
import com.kimtaeo.thecommercetoyproject.domain.member.service.SignUpMemberService;
import com.kimtaeo.thecommercetoyproject.domain.member.service.UpdateMemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
}
