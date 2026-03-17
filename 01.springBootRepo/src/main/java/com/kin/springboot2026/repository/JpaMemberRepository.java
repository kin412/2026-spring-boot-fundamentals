package com.kin.springboot2026.repository;

import com.kin.springboot2026.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    //jpa를 쓰려면 EntityManager를 주입받아야함. 얘가 datasource를 들고 통신을 담당함.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // jpa가 insert쿼리 만들고 member의 setter역할까지 다해줌.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); // 조회
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name).getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //jpql - 테이블대상으로 sql을 날리는것이 아닌, 객체를 대상으로 sql을 날림.
        List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();
        return result;
    }
}
