package com.homepage.localtum.basket.repository;

import com.homepage.localtum.domain.Basket;
import com.homepage.localtum.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BasketRepository extends JpaRepository<Basket, Long> {
    List<Basket> findByMemberAndCafename(String nickname, String cafename);
}
