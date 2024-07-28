package com.homepage.localtum.mypage.controller;

import com.homepage.localtum.mypage.dto.UpdateNicknameDto;
import com.homepage.localtum.mypage.service.MypageService;
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

    // 회원 정보 수정
    @PutMapping("/user")
    public ResponseEntity<CustomApiResponse<Void>> updateNickname(@RequestBody UpdateNicknameDto dto) {
        mypageService.updateNickname(dto);
        CustomApiResponse<Void> response = CustomApiResponse.createSuccess(HttpStatus.OK.value(), null, "닉네임이 수정되었습니다.");
        return ResponseEntity.ok(response);
    }
}
