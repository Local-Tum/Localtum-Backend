package com.homepage.localtum.coupon.controller;

import com.homepage.localtum.coupon.dto.AddCouponDto;
import com.homepage.localtum.coupon.repository.CouponRepository;
import com.homepage.localtum.coupon.service.CouponService;
import com.homepage.localtum.domain.Coupon;
import com.homepage.localtum.domain.Favorite;
import com.homepage.localtum.util.Member.AuthenticationMemberUtils;
import com.homepage.localtum.util.response.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/localtum/cafe_details/")
@RequiredArgsConstructor
public class CouponController {
    private final CouponService couponService;
    private final AuthenticationMemberUtils memberUtils;
    @PostMapping("/{cafe_name}/coupon")
    public ResponseEntity<CustomApiResponse<?>> createCoupon(@PathVariable("cafe_name") String cafename, @RequestBody AddCouponDto dto) {
        String currentMemberId = memberUtils.getCurrentMemberId();
       Coupon coupon = couponService.addCoupon(currentMemberId,cafename, dto.getDescription());
        CustomApiResponse<Coupon> response = CustomApiResponse.createSuccess(HttpStatus.OK.value(),coupon,"쿠폰이 적립되었습니다");
        return ResponseEntity.ok().body(response);
    }
}
