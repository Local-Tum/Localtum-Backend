package com.homepage.localtum.favorite.service;

import com.homepage.localtum.domain.Favorite;
import com.homepage.localtum.favorite.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    public Favorite addFavorite(String userName, String cafeName) {
        Favorite favorite = Favorite.builder()
                .userName(userName)
                .cafeName(cafeName)
                .build();
        return favoriteRepository.save(favorite);
    }

    public List<Favorite> getFavoritesByUserName(String userName) {
        return favoriteRepository.findByUserName(userName);
    }
}
