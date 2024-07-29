package com.homepage.localtum.favorite.repository;

import com.homepage.localtum.domain.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUserName(String userName);

    Optional<Favorite> findByUserNameAndCafeName(String nickname, String cafeName);
}