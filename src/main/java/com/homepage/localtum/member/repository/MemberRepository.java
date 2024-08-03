package com.homepage.localtum.member.repository;

import com.homepage.localtum.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findById(Long id);

     Optional<Member> findByMemberId(String memberId);
}
