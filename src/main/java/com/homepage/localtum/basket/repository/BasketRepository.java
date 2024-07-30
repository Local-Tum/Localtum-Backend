package com.homepage.localtum.basket.repository;

import com.homepage.localtum.domain.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BasketRepository extends JpaRepository<Basket, Long> {
    List<Basket> findByMemberAndCafenameAndBasketMenu(String nickname, String cafename,String basketMenu);
}
