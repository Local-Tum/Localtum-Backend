package com.homepage.localtum.favorite.controller;

import com.homepage.localtum.domain.Favorite;
import com.homepage.localtum.favorite.dto.AddFavoriteDto;
import com.homepage.localtum.favorite.service.FavoriteService;
import com.homepage.localtum.util.Member.AuthenticationMemberUtils;
import com.homepage.localtum.util.response.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/localtum")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final AuthenticationMemberUtils memberUtils;

    // 검색 - 즐겨찾기 등록
    @PostMapping("/search/like")
    public ResponseEntity<CustomApiResponse<Favorite>> addFavorite(@RequestBody AddFavoriteDto dto) {
        String currentMemberId = memberUtils.getCurrentMemberId();
        Favorite favorite = favoriteService.addFavorite(currentMemberId, dto.getCafeName());
        CustomApiResponse<Favorite> response = CustomApiResponse.createSuccess(HttpStatus.OK.value(), favorite, "즐겨찾기에 추가되었습니다");
        return ResponseEntity.ok(response);
    }

    // 검색 - 즐겨찾기 조회
    @GetMapping("/search/like")
    public ResponseEntity<CustomApiResponse<List<Favorite>>> getFavoritesByUserName() {
        String currentMemberId = memberUtils.getCurrentMemberId();
        List<Favorite> favorites = favoriteService.getFavoritesByUserNameAndSource(currentMemberId, "search");
        CustomApiResponse<List<Favorite>> response = CustomApiResponse.createSuccess(HttpStatus.OK.value(), favorites, "즐겨찾기 목록 조회 성공");
        return ResponseEntity.ok(response);
    }

    // 검색 - 즐겨찾기 삭제
    @DeleteMapping("/search/like")
    public ResponseEntity<CustomApiResponse<Void>> searchDeleteFavorite(@RequestBody AddFavoriteDto dto) {
        String currentMemberId = memberUtils.getCurrentMemberId();
        favoriteService.deleteFavorite(currentMemberId, dto.getCafeName());
        CustomApiResponse<Void> response = CustomApiResponse.createSuccess(HttpStatus.OK.value(), null, "즐겨찾기에서 삭제되었습니다");
        return ResponseEntity.ok(response);
    }

    // 상세정보 - 즐겨찾기 등록
    @PostMapping("/cafe_details/{cafe_name}")
    public ResponseEntity<CustomApiResponse<Favorite>> addFavoriteCafe(@PathVariable("cafe_name") @RequestBody String cafename) {
        String currentMemberId = memberUtils.getCurrentMemberId();
        Favorite favorite = favoriteService.addFavoriteCafe(currentMemberId, cafename);
        CustomApiResponse<Favorite> response = CustomApiResponse.createSuccess(HttpStatus.OK.value(), favorite, "즐겨찾기에 추가되었습니다");
        return ResponseEntity.ok(response);
    }

    // 상세정보 - 즐겨찾기 삭제
    @DeleteMapping("/cafe_details/{cafe_name}")
    public ResponseEntity<CustomApiResponse<String>> deleteFavoriteCafe(@PathVariable("cafe_name") String cafeName) {
        String currentMemberId = memberUtils.getCurrentMemberId();
        favoriteService.deleteFavorite(currentMemberId, cafeName);
        CustomApiResponse<String> response = CustomApiResponse.createSuccess(HttpStatus.OK.value(), null, "즐겨찾기가 삭제되었습니다.");
        return ResponseEntity.ok(response);
    }

    // 마이페이지 - 즐겨찾기 조회
    @GetMapping("/mypage/like_list")
    public ResponseEntity<CustomApiResponse<List<Favorite>>> getFavoritesMyPage() {
        String currentMemberId = memberUtils.getCurrentMemberId();
        List<Favorite> favorites = favoriteService.getFavoritesMyPage(currentMemberId);
        CustomApiResponse<List<Favorite>> response = CustomApiResponse.createSuccess(HttpStatus.OK.value(), favorites, "즐겨찾기 목록 조회 성공");
        return ResponseEntity.ok(response);
    }
}

