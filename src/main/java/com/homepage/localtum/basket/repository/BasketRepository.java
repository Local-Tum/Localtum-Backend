package com.homepage.localtum.basket.repository;

import com.homepage.localtum.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BasketRepository extends JpaRepository<Order, Long> {
}
