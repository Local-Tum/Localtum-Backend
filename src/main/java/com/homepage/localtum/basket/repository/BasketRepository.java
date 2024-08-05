package com.homepage.localtum.basket.repository;

import com.homepage.localtum.domain.Basket;
import com.homepage.localtum.domain.Member;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BasketRepository extends JpaRepository<Basket, Long> {
    List<Basket> findByMemberAndCafenameAndBasketMenu(String nickname, String cafename,String basketMenu);


    @Modifying
    @Transactional
    @Query("DELETE FROM Basket b WHERE b.memberId = :memberId AND b.cafename = :cafename AND b.basketMenu = :menuName")
    void deleteByMemberAndCafenameAndBasketMenu(String memberId, String cafename, String menuName);

    List<Basket> findByMemberIdAndCafenameAndBasketMenu(String memberId, String cafename, String menuName);
}
