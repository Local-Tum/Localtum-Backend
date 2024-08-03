package com.homepage.localtum.order.repository;

import com.homepage.localtum.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByMemberAndCafename(String nickname, String cafename);

    List<Order> findByMember(String nickname);
}
