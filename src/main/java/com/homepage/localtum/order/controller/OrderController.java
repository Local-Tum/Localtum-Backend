package com.homepage.localtum.order.controller;

import com.homepage.localtum.cafe.repository.CafeRepository;
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
@RequestMapping("/localtum/cafe_details/{cafe_name}")
@Builder
public class OrderController {
    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final AuthenticationMemberUtils memberUtils;
    @PostMapping("/{menu_name}")
    public ResponseEntity<CustomApiResponse<?>> createOrder(@PathVariable("cafe_name") String cafename,@PathVariable("menu_name") String menu_name,
                                                            @Valid @RequestBody AddOrderDto dto) {
        String currentMemberId = memberUtils.getCurrentMemberId();
        ResponseEntity<CustomApiResponse<?>> result =orderService.createOrder(currentMemberId,cafename,menu_name,dto);
        return result;
    }
}
