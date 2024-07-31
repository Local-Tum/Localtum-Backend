package com.homepage.localtum.order.service;

import com.homepage.localtum.basket.repository.BasketRepository;
import com.homepage.localtum.coupon.repository.CouponRepository;
import com.homepage.localtum.domain.*;
import com.homepage.localtum.member.repository.MemberRepository;
import com.homepage.localtum.order.dto.AddCoupon;
import com.homepage.localtum.order.repository.OrderRepository;
import com.homepage.localtum.stamp.StampRepository;
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
    private final StampRepository stampRepository;
    private final CouponRepository couponRepository;

    // 장바구니 주문
    public ResponseEntity<CustomApiResponse<?>> createOrder(String memberId, String cafename, String menuname, int des) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(memberId);

        if (optionalMember.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(CustomApiResponse.createFailWithoutData(HttpStatus.NOT_FOUND.value(), "아이디가 " + memberId + "인 회원은 존재하지 않습니다."));
        }

        Member member = optionalMember.get();

        // 회원의 장바구니 아이템을 모두 가져와서 주문으로 이동
        List<Basket> baskets = basketRepository.findByMemberAndCafenameAndBasketMenu(member.getNickname(), cafename, menuname);
        if (baskets.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(CustomApiResponse.createFailWithoutData(HttpStatus.BAD_REQUEST.value(), "주문할 장바구니 항목이 없습니다."));
        }

        List<Order> orders = new ArrayList<>();
        for (Basket basket : baskets) {
            Order order = Order.builder()
                    .member(member.getNickname())
                    .orderMenu(basket.getBasketMenu())
                    .size(basket.getSize())
                    .options(new ArrayList<>(basket.getOptions()))
                    .status(basket.getStatus())
                    .prices(basket.getPrices() - des)
                    .cafename(basket.getCafename())
                    .build();

            Order savedOrder = orderRepository.save(order);
            orders.add(savedOrder);
        }

        // 주문 후 장바구니 비우기
        basketRepository.deleteAll(baskets);

        // 스탬프 관리
        Optional<Stamp> optionalStamp = stampRepository.findByMemberIdAndCafename(memberId, cafename);
        Stamp stamp;
        if (optionalStamp.isPresent()) {
            stamp = optionalStamp.get();
            stamp.addStamps(orders.size(), couponRepository);
        } else {
            stamp = Stamp.builder()
                    .memberId(memberId)
                    .cafename(cafename)
                    .stampCount(orders.size())
                    .couponIssued(false)
                    .build();
            stamp.addStamps(0, couponRepository); // 초기 스탬프 추가
        }
        stampRepository.save(stamp);

        CustomApiResponse<List<Order>> result = CustomApiResponse.createSuccess(HttpStatus.OK.value(), orders, "주문이 완료되었습니다");
        return ResponseEntity.ok(result);
    }


    public ResponseEntity<CustomApiResponse<?>> getOrders(String memberId, String cafename) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(memberId);

        if (optionalMember.isEmpty()) {
            throw new RuntimeException("아이디가 " + memberId + "인 회원은 존재하지 않습니다.");
        }

        List<Order> orders = orderRepository.findByMemberAndCafename(optionalMember.get().getNickname(), cafename);
        if (orders.isEmpty()) {
            throw new RuntimeException("주문 내역이 없습니다.");
        }

        CustomApiResponse<List<Order>> result = CustomApiResponse.createSuccess(HttpStatus.OK.value(), orders, "주문 내역 조회 완료");
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<CustomApiResponse<?>> getAllOrders(String currentMemberId) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(currentMemberId);

        if (optionalMember.isEmpty()) {
            throw new RuntimeException("아이디가 " + currentMemberId + "인 회원은 존재하지 않습니다.");
        }

        List<Order> orders = orderRepository.findByMember(optionalMember.get().getNickname());
        if (orders.isEmpty()) {
            throw new RuntimeException("주문 내역이 없습니다.");
        }

        CustomApiResponse<List<Order>> result = CustomApiResponse.createSuccess(HttpStatus.OK.value(), orders, "주문 내역 조회 완료");
        return ResponseEntity.ok(result);
    }
}