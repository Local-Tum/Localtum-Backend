package com.homepage.localtum.coupon.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class AddCouponDto {
    private String couponname;
    private int description;
}
