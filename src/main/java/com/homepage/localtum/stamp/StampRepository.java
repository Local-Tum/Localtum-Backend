package com.homepage.localtum.stamp;


import com.homepage.localtum.domain.Stamp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StampRepository extends JpaRepository<Stamp, Long> {
    Optional<Stamp> findByMemberIdAndCafename(String memberId, String cafename);
    List<Stamp> findByMemberId(String memberId);
}