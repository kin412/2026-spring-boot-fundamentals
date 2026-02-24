package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

// junit5 라 @RunWith 생략
@SpringBootTest
class MemberInitTestRepositoryTest {

    @Autowired
    MemberFirstRepository memberFirstRepository;

    @Test
    @Transactional // @Transactional은 테스트에 있으면 끝나고 db를 롤백해버림
    @Rollback(false)
    public void testMember()throws Exception{
        //given
        MemberInitTest memberInitTest = new MemberInitTest();
        memberInitTest.setUsername("memberA");

        //when
        Long saveId = memberFirstRepository.save(memberInitTest);
        MemberInitTest findMemberInitTest = memberFirstRepository.find(saveId);

        //then
        Assertions.assertThat(findMemberInitTest.getId()).isEqualTo(memberInitTest.getId());
        Assertions.assertThat(findMemberInitTest.getUsername()).isEqualTo(memberInitTest.getUsername());
        Assertions.assertThat(findMemberInitTest).isEqualTo(memberInitTest);

        //지금 같은 @Transactional 안에 있다. = 영속성 컨텍스트에 있다. 그러므로 같다. jpa동일성 보장
        System.out.println("findMember == member : " + (findMemberInitTest == memberInitTest));

    }

}