package com.homepage.localtum.mypage.service;

import com.homepage.localtum.domain.Member;
import com.homepage.localtum.domain.Stamp;
import com.homepage.localtum.member.repository.MemberRepository;
import com.homepage.localtum.mypage.dto.UpdateNicknameDto;
import com.homepage.localtum.stamp.StampRepository;
import com.homepage.localtum.util.response.CustomApiResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MypageService {

    private final MemberRepository memberRepository;
    private final StampRepository stampRepository;

    // 회원 정보 수정
    @Transactional
    public Member updateNickname(String memberId, String nickname) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(memberId);

        if (optionalMember.isEmpty()) {
            throw new RuntimeException("아이디가 " + memberId + "인 회원은 존재하지 않습니다.");
        }

        Member member = optionalMember.get();
        member.updateNickname(nickname);
        return memberRepository.save(member);
    }

    // 스탬프 목록 조회
    public ResponseEntity<CustomApiResponse<List<Stamp>>> getStampList(String currentMemberId) {
        List<Stamp> stamps = stampRepository.findByMemberId(currentMemberId);
        if (stamps.isEmpty()) {
            throw new RuntimeException("스탬프 내역이 없습니다.");
        }

        CustomApiResponse<List<Stamp>> response = CustomApiResponse.createSuccess(HttpStatus.OK.value(), stamps, "스탬프 내역 조회 완료");
        return ResponseEntity.ok(response);
    }

    // 닉네임 조회
    public ResponseEntity<CustomApiResponse<String>> getNickname(String currentMemberId) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(currentMemberId);

        if (optionalMember.isEmpty()) {
            throw new RuntimeException("아이디가 " + currentMemberId + "인 회원은 존재하지 않습니다.");
        }

        Member member = optionalMember.get();
        String nickname = member.getNickname();

        CustomApiResponse<String> response = CustomApiResponse.createSuccess(HttpStatus.OK.value(), nickname, "닉네임 조회 완료");
        return ResponseEntity.ok(response);
    }
}
