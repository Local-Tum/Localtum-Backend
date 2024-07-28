package com.homepage.localtum.favorite.service;

import com.homepage.localtum.cafe.repository.CafeRepository;
import com.homepage.localtum.domain.Cafe;
import com.homepage.localtum.domain.Favorite;
import com.homepage.localtum.domain.Member;
import com.homepage.localtum.favorite.repository.FavoriteRepository;
import com.homepage.localtum.member.repository.MemberRepository;
import com.homepage.localtum.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final MemberRepository memberRepository;
    private final CafeRepository cafeRepository;
    public Favorite addFavorite(String memberId, String cafeName) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(memberId);
        if (optionalMember.isEmpty()) {
            throw new RuntimeException("아이디가 " + memberId + "인 회원은 존재하지 않습니다.");
        }

        Member member = optionalMember.get();

        Favorite favorite = Favorite.builder()
                .userName(member.getNickname())
                .cafeName(cafeName)
                .build();
        return favoriteRepository.save(favorite);
    }

    public List<Favorite> getFavoritesByUserName(String userName) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(userName);

        if (optionalMember.isEmpty()) {
            throw new RuntimeException("아이디가 " + userName + "인 회원은 존재하지 않습니다.");
        }
        Member member = optionalMember.get();

        return favoriteRepository.findByUserName(member.getNickname());
    }
}
