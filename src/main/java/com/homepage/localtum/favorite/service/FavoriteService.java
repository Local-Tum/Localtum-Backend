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

    // 검색 - 즐겨찾기 등록
    public Favorite addFavorite(String memberId, String cafeName) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(memberId);
        if (optionalMember.isEmpty()) {
            throw new RuntimeException("아이디가 " + memberId + "인 회원은 존재하지 않습니다.");
        }

        Member member = optionalMember.get();

        Favorite favorite = Favorite.builder()
                .userName(member.getNickname())
                .cafeName(cafeName)
                .source("search")  // Mark as added from search
                .build();
        return favoriteRepository.save(favorite);
    }

    // 검색 - 즐겨찾기 조회
    public List<Favorite> getFavoritesByUserNameAndSource(String userName, String source) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(userName);

        if (optionalMember.isEmpty()) {
            throw new RuntimeException("아이디가 " + userName + "인 회원은 존재하지 않습니다.");
        }
        Member member = optionalMember.get();

        return favoriteRepository.findByUserNameAndSource(member.getNickname(), source);
    }

    // 상세정보 - 즐겨찾기 등록
    public Favorite addFavoriteCafe(String memberId, String cafeName) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(memberId);
        if (optionalMember.isEmpty()) {
            throw new RuntimeException("아이디가 " + memberId + "인 회원은 존재하지 않습니다.");
        }

        Member member = optionalMember.get();

        Favorite favorite = Favorite.builder()
                .userName(member.getNickname())
                .cafeName(cafeName)
                .source("details")  // Mark as added from details
                .build();
        return favoriteRepository.save(favorite);
    }

    // 상세정보 - 즐겨찾기 삭제
    public void deleteFavorite(String memberId, String cafeName) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(memberId);
        if (optionalMember.isEmpty()) {
            throw new RuntimeException("아이디가 " + memberId + "인 회원은 존재하지 않습니다.");
        }

        Member member = optionalMember.get();
        Optional<Favorite> favorite = favoriteRepository.findByUserNameAndCafeName(member.getNickname(), cafeName);

        if (favorite.isPresent()) {
            favoriteRepository.delete(favorite.get());
        } else {
            throw new RuntimeException("즐겨찾기에 " + cafeName + "가 없습니다.");
        }
    }

    // 검색 - 즐겨찾기 삭제
    public void searchDeleteFavorite(String memberId, String cafeName) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(memberId);
        if (optionalMember.isEmpty()) {
            throw new RuntimeException("아이디가 " + memberId + "인 회원은 존재하지 않습니다.");
        }

        Member member = optionalMember.get();
        Optional<Favorite> favorite = favoriteRepository.findByUserNameAndCafeName(member.getNickname(), cafeName);
        if (favorite.isEmpty()) {
            throw new RuntimeException("즐겨찾기에 " + cafeName + "가 없습니다.");
        }

        favoriteRepository.delete(favorite.get());
    }

    // 마이페이지 즐겨찾기 조회
    public List<Favorite> getFavoritesMyPage(String userName) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(userName);

        if (optionalMember.isEmpty()) {
            throw new RuntimeException("아이디가 " + userName + "인 회원은 존재하지 않습니다.");
        }
        Member member = optionalMember.get();

        return favoriteRepository.findByUserName(member.getNickname());
    }
}
