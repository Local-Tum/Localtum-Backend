package com.homepage.localtum.domain.member.controller;

import com.homepage.localtum.domain.member.dto.SignInReqDto;
import com.homepage.localtum.domain.member.dto.SignUpDto;
import com.homepage.localtum.domain.member.service.MemberService;
import com.homepage.localtum.util.response.CustomApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/localTum")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpDto dto) {
        String result = memberService.signUp(dto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@RequestBody @Valid SignInReqDto dto) {
        ResponseEntity<CustomApiResponse<?>> result = memberService.signIn(dto);
        return result;
    }
}
