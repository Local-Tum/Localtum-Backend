package com.homepage.localtum.domain;

import com.homepage.localtum.coupon.repository.CouponRepository;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "STAMPS")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Stamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberId;
    private String cafename;

    @Column(name = "stamp_count", nullable = false)
    private int stampCount;

    @Column(name = "is_coupon_issued", nullable = false)
    private boolean couponIssued;
    // 스탬프를 추가하고 쿠폰을 발급할지 결정하는 메소드
    public void addStamps(int count, CouponRepository couponRepository) {
        this.stampCount += count;

        // 쿠폰 발급 조건 확인
        if (this.stampCount >= 10 && !this.couponIssued) {
            // 쿠폰 생성
            Coupon coupon = Coupon.builder()
                    .memberName(this.memberId)
                    .cafe_name(this.cafename)
                    .Coupon_description(500)
                    .build();
            couponRepository.save(coupon);

            // 쿠폰 발급 표시
            this.couponIssued = true;
        }
    }
}