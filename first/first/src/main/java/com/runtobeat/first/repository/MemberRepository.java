package com.runtobeat.first.repository;

import com.runtobeat.first.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
