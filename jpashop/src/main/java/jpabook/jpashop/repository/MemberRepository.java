package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor // 결과적으로 이 어노테이션으로 생성자 주입이 가능.
public class MemberRepository {

    //@PersistenceContext
    @Autowired
    private EntityManager em;

    //팩토리를 직접 주입받고 싶을때
    /*@PersistenceUnit
    private EntityManagerFactory emf;*/

    //@Autowired 사용시
    /*public MemberRepository(EntityManager em) {
        this.em = em;
    }*/

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name",Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
