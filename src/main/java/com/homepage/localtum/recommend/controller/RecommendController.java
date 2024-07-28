package com.homepage.localtum.recommend.controller;

import com.homepage.localtum.recommend.dto.MenuRecommendDto;
import com.homepage.localtum.recommend.dto.RecommendDto;
import com.homepage.localtum.util.response.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/localtum")
@RequiredArgsConstructor
public class RecommendController {

    @GetMapping("/recommend")
    public ResponseEntity<CustomApiResponse<MenuRecommendDto>> recommendMenu(@RequestParam String mood) {
        List<RecommendDto> recommendedMenu = getMenuByMood(mood);
//        if (recommendedMenu == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(CustomApiResponse.createFail(HttpStatus.NOT_FOUND.value(), "Mood not recognized"));
//        }
        MenuRecommendDto recommendation = new MenuRecommendDto(mood, recommendedMenu);
        return ResponseEntity.ok(CustomApiResponse.createSuccess(HttpStatus.OK.value(), recommendation, "Menu recommended successfully"));
    }

    private List<RecommendDto> getMenuByMood(String mood) {
        switch (mood.toLowerCase()) {
            case "우울해요":
                return Arrays.asList(
                        new RecommendDto("초코라떼", "http://example.com/images/choco_latte.jpg"),
                        new RecommendDto("카라멜 마키아토", "http://example.com/images/caramel_macchiato.jpg"),
                        new RecommendDto("딸기 밀크 셰이크", "http://example.com/images/strawberry_milkshake.jpg")
                );
            case "기분이 좋아요":
                return Arrays.asList(
                        new RecommendDto("아이스 그린티 라떼", "http://example.com/images/iced_green_tea_latte.jpg"),
                        new RecommendDto("패션후르츠 스무디", "http://example.com/images/passionfruit_smoothie.jpg"),
                        new RecommendDto("민트 모히토", "http://example.com/images/mint_mojito.jpg")
                );
            case "스트레스 받아요":
                return Arrays.asList(
                        new RecommendDto("레몬 진저티", "http://example.com/images/lemon_ginger_tea.jpg"),
                        new RecommendDto("페퍼민트 차", "http://example.com/images/peppermint_tea.jpg"),
                        new RecommendDto("바닐라 카모마일 티", "http://example.com/images/vanilla_chamomile_tea.jpg")
                );
            case "피곤해요":
                return Arrays.asList(
                        new RecommendDto("에스프레소", "http://example.com/images/espresso.jpg"),
                        new RecommendDto("아이스 아메리카노", "http://example.com/images/iced_americano.jpg"),
                        new RecommendDto("홍삼 라떼", "http://example.com/images/red_ginseng_latte.jpg")
                );
            case "편안해요":
                return Arrays.asList(
                        new RecommendDto("바닐라 밀크티", "http://example.com/images/vanilla_milk_tea.jpg"),
                        new RecommendDto("오레오 쉐이크", "http://example.com/images/oreo_shake.jpg"),
                        new RecommendDto("헤이즐넛 라떼", "http://example.com/images/hazelnut_latte.jpg")
                );
            case "집중하고 싶어요":
                return Arrays.asList(
                        new RecommendDto("마차 라떼", "http://example.com/images/matcha_latte.jpg"),
                        new RecommendDto("블랙 커피", "http://example.com/images/black_coffee.jpg"),
                        new RecommendDto("로즈 마리 티", "http://example.com/images/rosemary_tea.jpg")
                );
            default:
                return null;
        }
    }
}
