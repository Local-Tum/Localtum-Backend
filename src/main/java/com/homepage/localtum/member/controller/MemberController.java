package com.homepage.localtum.member.controller;

import com.homepage.localtum.member.dto.SignInReqDto;
import com.homepage.localtum.member.dto.SignUpDto;
import com.homepage.localtum.member.service.MemberService;
import com.homepage.localtum.util.response.CustomApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/localtum")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/signUp")
    public ResponseEntity<CustomApiResponse<?>> signUp(@RequestBody @Valid SignUpDto dto) {
        ResponseEntity<CustomApiResponse<?>> result = memberService.signUp(dto);
        return result;
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@RequestBody @Valid SignInReqDto dto) {
        ResponseEntity<CustomApiResponse<?>> result = memberService.signIn(dto);
        return result;
    }
}
