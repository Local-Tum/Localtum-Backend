package com.homepage.localtum.cafe.controller;

import com.homepage.localtum.cafe.dto.CreateCafeDto;
import com.homepage.localtum.cafe.repository.CafeRepository;
import com.homepage.localtum.cafe.service.CafeService;
import com.homepage.localtum.domain.Cafe;
import com.homepage.localtum.order.service.OrderService;
import com.homepage.localtum.util.Member.AuthenticationMemberUtils;
import com.homepage.localtum.util.response.CustomApiResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/localtum")
@RequiredArgsConstructor
public class CafeController {

    private final CafeService cafeService;
    private final AuthenticationMemberUtils memberUtils;

//    @Transactional
//    @PostMapping("/cafe_details")
//    public ResponseEntity<CustomApiResponse<?>> createCafe(@Valid @RequestBody CreateCafeDto dto){
//        ResponseEntity<CustomApiResponse<?>> result = cafeService.createCafe(dto);
//        return result;
//    }
//
//    @GetMapping("/cafe_details/{cafe_name}")
//    public ResponseEntity<CustomApiResponse<?>> getCafeById(@PathVariable("cafe_name") String cafeName){
//        ResponseEntity<CustomApiResponse<?>> result =cafeService.getCafeById(cafeName);
//        return result;
//    }

//    @GetMapping("/search/like/list")
//    public List<Cafe> getAllCafes() {
//        return cafeService.getAllCafes();
//    }

    // 스탬프 조회
    @GetMapping("/cafe_details/{cafe_name}")
    public ResponseEntity<CustomApiResponse<?>> getStamps(@PathVariable("cafe_name") String cafename) {
        String currentMemberId = memberUtils.getCurrentMemberId();
        ResponseEntity<CustomApiResponse<?>> result = cafeService.getStamps(currentMemberId, cafename);
        return result;
    }
}