package com.homepage.localtum.order.controller;

import com.homepage.localtum.cafe.repository.CafeRepository;
import com.homepage.localtum.domain.Order;
import com.homepage.localtum.order.dto.AddCoupon;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/localtum")
@Builder
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final AuthenticationMemberUtils memberUtils;

    // 상세화면 커피 주문 결제(장바구니 기반)
    @PostMapping("/cafe_details/{cafe_name}/{menu_name}/order_basket")
    public ResponseEntity<CustomApiResponse<?>> createOrderWithBasket(@PathVariable("cafe_name") String cafename,@PathVariable("menu_name") String menu_name,
                                                            @RequestBody AddCoupon dto) {
        String currentMemberId = memberUtils.getCurrentMemberId();
        int des =dto.getCoupon();
        ResponseEntity<CustomApiResponse<?>> result =orderService.createOrderWithBasket(currentMemberId,cafename,menu_name,des);
        return result;
    }

    // 상세화면 - 방금 주문한 내역 보기
    @GetMapping("/cafe_details/{cafe_name}/{menu_name}/order_history")
    public ResponseEntity<CustomApiResponse<?>> getOrders(@PathVariable("cafe_name") String cafename) {
        String currentMemberId = memberUtils.getCurrentMemberId();
        ResponseEntity<CustomApiResponse<?>> result = orderService.getOrders(currentMemberId, cafename);
        return result;
    }

    // 주문내역 - 주문내역 조회
    @GetMapping("order_list")
    public ResponseEntity<CustomApiResponse<?>> getAllOrders() {
        String currentMemberId = memberUtils.getCurrentMemberId();
        ResponseEntity<CustomApiResponse<?>> result = orderService.getAllOrders(currentMemberId);
        return result;
    }
    @PostMapping("/cafe_details/{cafe_name}/{menu_name}/order")
    public ResponseEntity<CustomApiResponse<?>> createOrder(@PathVariable("cafe_name") String cafename, @PathVariable("menu_name") String menu_name,
                                                            @RequestBody AddOrderDto dto) {
        String currentMemberId = memberUtils.getCurrentMemberId();
        int des =dto.getCoupon();
        ResponseEntity<CustomApiResponse<?>> result =orderService.createOrder(currentMemberId,cafename,menu_name,des,dto);
        return result;
    }
}
