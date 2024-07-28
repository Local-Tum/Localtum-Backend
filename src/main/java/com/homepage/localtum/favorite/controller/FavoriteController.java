package com.homepage.localtum.favorite.controller;

import com.homepage.localtum.domain.Favorite;
import com.homepage.localtum.favorite.dto.AddFavoriteDto;
import com.homepage.localtum.favorite.service.FavoriteService;
import com.homepage.localtum.util.response.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/localtum/search/like")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    // 즐겨찾기 등록
    @PostMapping
    public ResponseEntity<CustomApiResponse<Favorite>> addFavorite(@RequestBody AddFavoriteDto dto) {
        Favorite favorite = favoriteService.addFavorite(dto.getUserId(), dto.getCafeId());
        CustomApiResponse<Favorite> response = CustomApiResponse.createSuccess(HttpStatus.OK.value(), favorite, "즐겨찾기에 추가되었습니다");
        return ResponseEntity.ok(response);
    }

    // 즐겨찾기 조회
    @GetMapping("/{userId}")
    public ResponseEntity<CustomApiResponse<List<Favorite>>> getFavoritesByUserId(@PathVariable Long userId) {
        List<Favorite> favorites = favoriteService.getFavoritesByUserId(userId);
        CustomApiResponse<List<Favorite>> response = CustomApiResponse.createSuccess(HttpStatus.OK.value(), favorites, "즐겨찾기 목록 조회 성공");
        return ResponseEntity.ok(response);
    }
}
