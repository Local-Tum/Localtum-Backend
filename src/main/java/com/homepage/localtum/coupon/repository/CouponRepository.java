package com.homepage.localtum.coupon.repository;

import com.homepage.localtum.domain.Coupon;
import com.homepage.localtum.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CouponRepository extends JpaRepository<Coupon, Long> {
    List<Coupon> findBymemberName(String nickname);

    List<Coupon> findBymemberId(String memberId);

    Coupon findByCouponName(String couponName);

    //Coupon findBymemeberIdAndCouponName(String memberId, String couponName);

    //Optional<Coupon> findByMemeberIdAndCouponName(String memberId, String couponName);

    Optional<Coupon> findByMemberIdAndCouponName(String memberId, String couponName);
}
