package com.homepage.localtum.mypage.controller;

import com.homepage.localtum.domain.Member;
import com.homepage.localtum.domain.Stamp;
import com.homepage.localtum.mypage.dto.UpdateNicknameDto;
import com.homepage.localtum.mypage.service.MypageService;
import com.homepage.localtum.util.Member.AuthenticationMemberUtils;
import com.homepage.localtum.util.response.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/localtum/mypage")
@RequiredArgsConstructor
public class MypageController {

    private final MypageService mypageService;
    private final AuthenticationMemberUtils memberUtils;

    // 회원 닉네임 수정
    @PutMapping("/user")
    public ResponseEntity<CustomApiResponse<String>> updateNickname(@RequestBody UpdateNicknameDto dto) {
        String currentMemberId = memberUtils.getCurrentMemberId();
        Member member = mypageService.updateNickname(currentMemberId, dto.getNickname());
        String updatedNickname = member.getNickname();
        CustomApiResponse<String> response = CustomApiResponse.createSuccess(HttpStatus.OK.value(), updatedNickname, "닉네임이 수정되었습니다.");
        return ResponseEntity.ok(response);
    }

    // 회원 닉네임 조회
    @GetMapping("/user")
    public ResponseEntity<CustomApiResponse<String>> getNickname() {
        String currentMemberId = memberUtils.getCurrentMemberId();
        return mypageService.getNickname(currentMemberId);
    }


    // 전체 스탬프 조회
    @GetMapping("/stamp")
    public ResponseEntity<CustomApiResponse<List<Stamp>>> getStampList() {
        String currentMemberId = memberUtils.getCurrentMemberId();
        return mypageService.getStampList(currentMemberId);
    }
}
