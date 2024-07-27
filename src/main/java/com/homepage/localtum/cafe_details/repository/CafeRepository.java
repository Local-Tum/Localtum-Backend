package com.homepage.localtum.cafe_details.repository;

import com.homepage.localtum.domain.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CafeRepository extends JpaRepository<Cafe, Long> {

    Optional<Cafe> findByName(String cafeName);
}
