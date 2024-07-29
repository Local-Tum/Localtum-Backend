package com.homepage.localtum.order.service;

import com.homepage.localtum.domain.Member;
import com.homepage.localtum.domain.Order;
import com.homepage.localtum.member.repository.MemberRepository;
import com.homepage.localtum.order.dto.AddOrderDto;
import com.homepage.localtum.order.repository.OrderRepository;
import com.homepage.localtum.util.response.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    public ResponseEntity<CustomApiResponse<?>> createOrder(String memberId,String cafename,String menu, AddOrderDto dto) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(memberId);

        if (optionalMember.isEmpty()) {
            throw new RuntimeException("아이디가 " + memberId + "인 회원은 존재하지 않습니다.");
        }
        Member member = optionalMember.get();
        Order order = Order.builder()
                .member(member.getNickname())
                .orderMemu(menu)
                .size(dto.getSize())
                .options(dto.getOptions())
                .status(dto.getStatus())
                .prices(dto.getPrices())
                .cafename(cafename)
                .build();
        Order savedOrder = orderRepository.save(order);
        CustomApiResponse<Order> result = CustomApiResponse.createSuccess(HttpStatus.OK.value(),savedOrder ,"데이터가 저장되었습니다");
        return ResponseEntity.ok(result);
    }
}
