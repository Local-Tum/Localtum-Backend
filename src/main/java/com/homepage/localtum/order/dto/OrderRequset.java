package com.homepage.localtum.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequset {
    private AddCoupon coupon;
    private AddOrderDto order;
}
