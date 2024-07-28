package com.homepage.localtum.coupon.repository;

import com.homepage.localtum.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
