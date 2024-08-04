package com.homepage.localtum.order.repository;

import com.homepage.localtum.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByMemberAndCafename(String nickname, String cafename);

    List<Order> findByMember(String nickname);

    @Query("SELECT o FROM Order o WHERE o.member = :member AND o.cafename = :cafename ORDER BY o.createdAt DESC")
    List<Order> findLatestOrder(@Param("member") String member, @Param("cafename") String cafename);
}
