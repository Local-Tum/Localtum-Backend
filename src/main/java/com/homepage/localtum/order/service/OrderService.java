package com.homepage.localtum.order.service;

import com.homepage.localtum.basket.repository.BasketRepository;
import com.homepage.localtum.domain.Basket;
import com.homepage.localtum.domain.Member;
import com.homepage.localtum.domain.Order;
import com.homepage.localtum.member.repository.MemberRepository;
import com.homepage.localtum.order.repository.OrderRepository;
import com.homepage.localtum.util.response.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final BasketRepository basketRepository;
    private final MemberRepository memberRepository;

    public ResponseEntity<CustomApiResponse<?>> createOrder(String memberId, String cafename,String menuname) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(memberId);

        if (optionalMember.isEmpty()) {
            throw new RuntimeException("아이디가 " + memberId + "인 회원은 존재하지 않습니다.");
        }

        Member member = optionalMember.get();

        // 회원의 장바구니 아이템을 모두 가져와서 주문으로 이동
        List<Basket> baskets = basketRepository.findByMemberAndCafenameAndBasketMenu(member.getNickname(), cafename,menuname);
        if (baskets.isEmpty()) {
            throw new RuntimeException("주문할 장바구니 항목이 없습니다.");
        }

        for (Basket basket : baskets) {
            Order order = Order.builder()
                    .member(member.getNickname())
                    .orderMenu(basket.getBasketMenu())
                    .size(basket.getSize())
                    .options(new ArrayList<>(basket.getOptions()))
                    .status(basket.getStatus())
                    .prices(basket.getPrices())
                    .cafename(basket.getCafename())
                    .build();

            Order saved = orderRepository.save(order);
        }

        // 주문 후 장바구니 비우기
        basketRepository.deleteAll(basketRepository.findAll());

        CustomApiResponse<List<Order>> result = CustomApiResponse.createSuccess(HttpStatus.OK.value(), null,"주문이 완료되었습니다");
        return ResponseEntity.ok(result);
    }
}