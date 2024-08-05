package com.homepage.localtum.domain;

import com.homepage.localtum.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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

    @Column(name = "cafe_name")
    private String cafeName;

    @Column(name = "coupon_description")
    private int couponDescription;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "coupon_name")
    private String couponName;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "coupon_status")
    private CouponStatus couponStatus;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @PrePersist
    public void setExpirationDate() {
        if (getCreatedAt() != null) {
            this.expirationDate = getCreatedAt().plus(1, ChronoUnit.MONTHS);
        }
    }

}
