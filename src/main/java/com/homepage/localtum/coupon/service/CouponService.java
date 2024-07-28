package com.homepage.localtum.coupon.service;

import com.homepage.localtum.coupon.repository.CouponRepository;
import com.homepage.localtum.domain.Coupon;
import com.homepage.localtum.domain.Member;
import com.homepage.localtum.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;
    private final MemberRepository memberRepository;
    public Coupon addCoupon(String memberId, String cafename, int description) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(memberId);
        if (optionalMember.isEmpty()) {
            throw new RuntimeException("아이디가 " + memberId + "인 회원은 존재하지 않습니다.");
        }
        Member member = optionalMember.get();
        Coupon coupon1 =Coupon.builder()
                .cafe_name(cafename)
                .Member_name(member.getNickname())
                .Coupon_description(description)
                .build();
        return couponRepository.save(coupon1);
    }
}
