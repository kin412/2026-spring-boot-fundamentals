package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    @PersistenceContext // 엔티티 매니저 autowired 해줌
    private EntityManager em;

    public Long save(MemberInitTest memberInitTest) {
        em.persist(memberInitTest);
        return memberInitTest.getId();
    }

    public MemberInitTest find(Long id) {
        return em.find(MemberInitTest.class, id);
    }

}
