package com.homepage.localtum.mypage.service;

import com.homepage.localtum.domain.Member;
import com.homepage.localtum.member.repository.MemberRepository;
import com.homepage.localtum.mypage.dto.UpdateNicknameDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MypageService {
    private final MemberRepository memberRepository;


    public Member updateNickname(String memberId, String nickname) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(memberId);

        if (optionalMember.isEmpty()) {
            throw new RuntimeException("아이디가 " + memberId + "인 회원은 존재하지 않습니다.");
        }

        Member member = optionalMember.get();

//        member.updateNickname(nickname);
        return memberRepository.save(member.updateNickname(nickname));
    }


}
