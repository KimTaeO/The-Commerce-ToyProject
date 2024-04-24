package com.kimtaeo.thecommercetoyproject.domain.member.presentation;

import com.kimtaeo.thecommercetoyproject.domain.member.enums.SortType;
import com.kimtaeo.thecommercetoyproject.domain.member.presentation.dto.request.SignUpRequest;
import com.kimtaeo.thecommercetoyproject.domain.member.presentation.dto.response.PagedMembersResponse;
import com.kimtaeo.thecommercetoyproject.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<Void> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        memberService.signUp(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/list")
    public ResponseEntity<PagedMembersResponse> queryMembers(
            @RequestParam Integer page,
            @RequestParam Integer pageSize,
            @RequestParam SortType sort
    ) {
        PagedMembersResponse pagedMembersResponse = memberService.queryMembers(page, pageSize, sort);

        return ResponseEntity.status(HttpStatus.OK).body(pagedMembersResponse);
    }
}
