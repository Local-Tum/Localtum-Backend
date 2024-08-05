package com.homepage.localtum.basket.service;

import com.homepage.localtum.basket.dto.AddbasketDto;
import com.homepage.localtum.basket.repository.BasketRepository;
import com.homepage.localtum.domain.Basket;
import com.homepage.localtum.domain.Cafe;
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

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;
    private final MemberRepository memberRepository;

    //장바구니 채우기
    public ResponseEntity<CustomApiResponse<?>> createBasket(String memberId,String cafename,String basketMenu, AddbasketDto dto) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(memberId);

        if (optionalMember.isEmpty()) {
            throw new RuntimeException("아이디가 " + memberId + "인 회원은 존재하지 않습니다.");
        }
        Member member = optionalMember.get();
        Basket basket = Basket.builder()
                .member(member.getNickname())
                .basketMenu(basketMenu)
                .memberId(member.getMemberId())
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

    //장바구니 비우기
    public ResponseEntity<CustomApiResponse<?>> deleteBakset(String currentMemberId, String cafename, String menuName) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(currentMemberId);
        if (optionalMember.isEmpty()) {
            throw new RuntimeException("아이디가 " + currentMemberId + "인 회원은 존재하지 않습니다.");
        }
        Member member = optionalMember.get();

        List<Basket> basketsToDelete = basketRepository.findByMemberIdAndCafenameAndBasketMenu(member.getMemberId(), cafename, menuName);
        if (basketsToDelete.isEmpty()) {
            CustomApiResponse<?> res=CustomApiResponse.createFailWithout(HttpStatus.NOT_FOUND.value() ,"장바구니에 해당항목이 존재하지 않습니다");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }


        basketRepository.deleteByMemberAndCafenameAndBasketMenu(member.getMemberId(), cafename, menuName);
        CustomApiResponse<List<Basket>> res = CustomApiResponse.createSuccess(HttpStatus.OK.value(),null,"장바구니 비우기 성공");
        return ResponseEntity.ok(res);

    }
}
