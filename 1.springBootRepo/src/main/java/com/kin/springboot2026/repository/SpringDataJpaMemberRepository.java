package com.kin.springboot2026.repository;

import com.kin.springboot2026.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//JpaRepository를 extends 받으면 spring data jpa가 구현체를 자동으로 만들어 bean을 등록함.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {


    @Override
    Optional<Member> findByName(String name);
}
