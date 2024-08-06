package com.homepage.localtum.domain;

import com.homepage.localtum.coupon.repository.CouponRepository;
import com.homepage.localtum.order.repository.OrderRepository;
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
    private int i;
    @Column(name = "coupon_count", nullable = false)
    private int couponCount;
    // 스탬프를 추가하고 쿠폰을 발급할지 결정하는 메소드

    public void addStamps(int count, CouponRepository couponRepository) {
        this.stampCount += count;
        while (this.stampCount >= 10) {
            this.stampCount -= 10;
            Coupon coupon = Coupon.builder()
                    .memberId(this.memberId)
                    .cafeName(this.cafename)
                    .couponDescription(500)
                    .couponStatus(CouponStatus.UNUSED)
                    .couponName(this.cafename + (this.couponCount + 1))
                    .build();
            couponRepository.save(coupon);
            i+=1;
            this.couponCount += 1;
        }
    }

}