package com.homepage.localtum.mypage.controller;

import com.homepage.localtum.domain.Member;
import com.homepage.localtum.mypage.dto.UpdateNicknameDto;
import com.homepage.localtum.mypage.service.MypageService;
import com.homepage.localtum.util.Member.AuthenticationMemberUtils;
import com.homepage.localtum.util.response.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/localtum/mypage")
@RequiredArgsConstructor
public class MypageController {

    private final MypageService mypageService;
    private final AuthenticationMemberUtils memberUtils;

    // 회원 정보 수정
    @PutMapping("/user")
    public ResponseEntity<CustomApiResponse<Member>> updateNickname(@RequestBody UpdateNicknameDto dto) {
        String currentMemberId = memberUtils.getCurrentMemberId();
        Member member = mypageService.updateNickname(currentMemberId, dto.getNickname());
        CustomApiResponse<Member> response = CustomApiResponse.createSuccess(HttpStatus.OK.value(), member, "닉네임이 수정되었습니다.");
        return ResponseEntity.ok(response);
    }
}
