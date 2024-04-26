package com.kimtaeo.thecommercetoyproject.domain.member.presentation;

import com.kimtaeo.thecommercetoyproject.domain.member.service.QueryMembersService;
import com.kimtaeo.thecommercetoyproject.domain.member.service.SignUpMemberService;
import com.kimtaeo.thecommercetoyproject.domain.member.service.UpdateMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

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
}
