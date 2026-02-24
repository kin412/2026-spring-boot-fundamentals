package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    //실제 반영하고 싶다면
    /*@Autowired
    EntityManager em;*/

    @Test
    //@Rollback(false) // 테스트에서는 자동으로 트랜잭션이 롤백인데 꼭 보고싶은경우
    public void 회원가입() throws Exception {

        //given
        Member member = new Member();
        member.setName("kim");


        //when
        Long savedId = memberService.join(member);

        //then
        //em.flush(); // 플러시 하게되면 실제 반영
        Assertions.assertEquals(member, memberRepository.findOne(savedId));

    }

    @Test // (expected = IllegalStateException) - junit4에서 쓰던방식. 5에선 아래 thrownby를 사용
    public void 중복_회원_예외() throws Exception {

        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
       /* try {
            memberService.join(member2); //만들어둔 예외가 발생해야 한다!
            fail("예외가 발생해야한다.");
        } catch (IllegalStateException e){
            return ;
        }*/

        //then
        //fail("예외가 발생해야한다.");

        //junit5
       org.assertj.core.api.Assertions.assertThatThrownBy(() -> memberService.join(member2))
                //.as("예외가 발생해야한다!")
                //.withFailMessage("예외가 발생해야 한다!")
                .isInstanceOf(IllegalStateException.class);
        //fail("예외가 발생해야한다."); // try catch시에만 이거대신 assertThatThrownBy

    }

}