package com.homepage.localtum.mypage.service;

import com.homepage.localtum.domain.Member;
import com.homepage.localtum.member.repository.MemberRepository;
import com.homepage.localtum.mypage.dto.UpdateNicknameDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MypageService {
    private final MemberRepository memberRepository;

    @Transactional
    public void updateNickname(UpdateNicknameDto dto) {
        Member member = memberRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("회원 정보를 찾을 수 없습니다."));

        member.updateNickname(dto.getNickname());
        memberRepository.save(member);
    }
}
