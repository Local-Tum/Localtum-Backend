package com.homepage.localtum.domain;

import com.homepage.localtum.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "COPUONS")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cafe_name;
    private int Coupon_description;
    private String memberId;
    private String memberName;
    private String couponName;
    @Enumerated(EnumType.STRING)
    private CouponStatus couponStatus;

    public void setCouponStatus(CouponStatus couponStatus) {
        this.couponStatus = couponStatus;
    }
}
