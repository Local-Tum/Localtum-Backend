package com.homepage.localtum.basket.controller;

import com.homepage.localtum.basket.dto.AddbasketDto;
import com.homepage.localtum.basket.repository.BasketRepository;
import com.homepage.localtum.basket.service.BasketService;
import com.homepage.localtum.order.dto.AddOrderDto;
import com.homepage.localtum.order.repository.OrderRepository;
import com.homepage.localtum.order.service.OrderService;
import com.homepage.localtum.util.Member.AuthenticationMemberUtils;
import com.homepage.localtum.util.response.CustomApiResponse;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/localtum/cafe_details/{cafe_name}")
@Builder
public class BasketController {
    private final BasketRepository basketRepository;
    private final BasketService basketService;
    private final AuthenticationMemberUtils memberUtils;

    @PostMapping("/{menu_name}")
    public ResponseEntity<CustomApiResponse<?>> createOrder(@PathVariable("cafe_name") String cafename,@PathVariable("menu_name") String menu_name,
                                                            @Valid @RequestBody AddbasketDto dto) {
        String currentMemberId = memberUtils.getCurrentMemberId();
        ResponseEntity<CustomApiResponse<?>> result =basketService.createBasket(currentMemberId,cafename,menu_name,dto);
        return result;
    }

    //장바구니 비우기
    @DeleteMapping("/{menu_name}")
    public ResponseEntity<CustomApiResponse<?>> deleteOrder(@PathVariable("cafe_name") String cafename,@PathVariable("menu_name") String menu_name) {
        String currentMemberId = memberUtils.getCurrentMemberId();
        ResponseEntity<CustomApiResponse<?>> result =basketService.deleteBakset(currentMemberId,cafename,menu_name);
        return result;
    }
}
