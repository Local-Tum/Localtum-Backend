package com.homepage.localtum.order.repository;

import com.homepage.localtum.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long> {
}
