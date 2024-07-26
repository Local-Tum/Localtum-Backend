package com.homepage.localtum.domain.cafe_details.repository;

import com.homepage.localtum.domain.cafe_details.dto.CreateCafeDto;
import com.homepage.localtum.domain.cafe_details.entitiy.Cafe;
import com.homepage.localtum.util.response.CustomApiResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface CafeRepository extends JpaRepository<Cafe, Long> {

    Optional<Cafe> findByName(String cafeName);
}
