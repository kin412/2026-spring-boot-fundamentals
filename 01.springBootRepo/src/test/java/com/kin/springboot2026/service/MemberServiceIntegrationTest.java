package com.kin.springboot2026.service;

import com.kin.springboot2026.domain.Member;
import com.kin.springboot2026.repository.MemberRepository;
import com.kin.springboot2026.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// 스프링 부트식 테스트
@SpringBootTest // 스프링 컨테이너와 테스트를 함께 실행
@Transactional // 테스트코드에서는 무조건 끝나고 롤백함
class MemberServiceIntegrationTest {

    @Autowired // 테스트는 결국 테스트하기만 하면 되는거라 번거롭게 생성자 주입 이런거 안하고 걍 필드주입함
    MemberService memberService;
    @Autowired
    MemberRepository memoryMemberRepository;



    //테스트 코드에서의 메서드명은 한글로 써도 되기도함
    @Test
    void 회원가입() {
        //given - 뭔가가 주어졌는데
        Member member = new Member();
        member.setName("spring99");

        //when - 이걸 실행했을때
        Long saveId = memberService.join(member);

        //then - 결과가 이게 나와야한다.
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring99");

        Member member2 = new Member();
        member2.setName("spring99");

        //when

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //assertThrows(NullPointerException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*try{
            memberService.join(member2);
            //fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/




        //then
    }

}