package com.homepage.localtum.coupon.repository;

import com.homepage.localtum.domain.Coupon;
import com.homepage.localtum.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CouponRepository extends JpaRepository<Coupon, Long> {
    List<Coupon> findBymemberName(String nickname);

    List<Coupon> findBymemberId(String memberId);
}
