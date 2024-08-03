package com.homepage.localtum.coupon.service;

import com.homepage.localtum.coupon.repository.CouponRepository;
import com.homepage.localtum.domain.Coupon;
import com.homepage.localtum.domain.Member;
import com.homepage.localtum.member.repository.MemberRepository;
import com.homepage.localtum.util.response.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
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
                .memberName(member.getNickname())
                .Coupon_description(description)
                .build();
        return couponRepository.save(coupon1);
    }
    public ResponseEntity<CustomApiResponse<?>> getAllCoupons(String memberId) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(memberId);

        if (optionalMember.isEmpty()) {
            throw new RuntimeException("아이디가 " + memberId + "인 회원은 존재하지 않습니다.");
        }
        Member member = optionalMember.get();
        List<Coupon> coupon=couponRepository.findBymemberName(member.getMemberId());
        CustomApiResponse<List<Coupon>> response = CustomApiResponse.createSuccess(HttpStatus.OK.value(),coupon,"쿠폰 조회가 완료되었습니다");
        return ResponseEntity.ok(response);
    }
}
