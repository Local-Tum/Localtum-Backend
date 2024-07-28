package com.homepage.localtum.favorite.service;

import com.homepage.localtum.domain.Favorite;
import com.homepage.localtum.favorite.repository.FavoriteRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private FavoriteRepository favoriteRepository;

    public Favorite addFavorite(Long userId, Long cafeId) {
        Favorite favorite = Favorite.builder()
                .userId(userId)
                .cafeId(cafeId)
                .build();
        return favoriteRepository.save(favorite);
    }

    public List<Favorite> getFavoritesByUserId(Long userId) {
        return favoriteRepository.findByUserId(userId);
    }
}
