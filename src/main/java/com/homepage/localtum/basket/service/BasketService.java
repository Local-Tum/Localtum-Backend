package com.homepage.localtum.basket.service;

import com.homepage.localtum.basket.dto.AddbasketDto;
import com.homepage.localtum.basket.repository.BasketRepository;
import com.homepage.localtum.domain.Basket;
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
public class BasketService {
    private final BasketRepository basketRepository;
    private final MemberRepository memberRepository;
    public ResponseEntity<CustomApiResponse<?>> createBasket(String memberId,String cafename,String basketMenu, AddbasketDto dto) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(memberId);

        if (optionalMember.isEmpty()) {
            throw new RuntimeException("아이디가 " + memberId + "인 회원은 존재하지 않습니다.");
        }
        Member member = optionalMember.get();
        Basket basket = Basket.builder()
                .member(member.getNickname())
                .basketMenu(basketMenu)
                .size(dto.getSize())
                .options(dto.getOptions())
                .status(dto.getStatus())
                .prices(dto.getPrices())
                .cafename(cafename)
                .build();
        Basket savedBasket = basketRepository.save(basket);
        CustomApiResponse<Basket> result = CustomApiResponse.createSuccess(HttpStatus.OK.value(),savedBasket ,"데이터가 저장되었습니다");
        return ResponseEntity.ok(result);
    }
}
