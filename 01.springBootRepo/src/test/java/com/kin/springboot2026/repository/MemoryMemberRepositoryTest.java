package com.kin.springboot2026.repository;

import com.kin.springboot2026.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();

    //@BeforeEach //test메서드 하나가 시작할때마다 동작하는 어노테이션

    //테스트가 끝날때마다 repository를 지워주는 코드
    @AfterEach //test메서드 하나가 끝날때마다 동작하는 어노테이션
    public void afterEach() {
        memoryMemberRepository.clearStore();
    }

    @Test
    public void save(){
        // 이렇게 하고 옆의 재생버튼을 누르면 그냥 해당 메소드를 실행함
        Member member = new Member();
        member.setName("spring");

        memoryMemberRepository.save(member);

        Member result = memoryMemberRepository.findById(member.getId()).get();

        //System.out.println("result : " + (result == member));
        //Assertions.assertEquals(member, result); //junit5 소속 라이브러리 Assertions
        //Assertions.assertThat(member).isEqualTo(result); //assertj 소속 라이브러리 Assertions
        assertThat(member).isEqualTo(result); //바로 윗 코드의 assertions를 7라인의 static으로 임포트 해서 사용하는 방법.
    }

    @Test
    public void findByName(){

        Member member1 = new Member();
        member1.setName("spring1");
        memoryMemberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring22");
        memoryMemberRepository.save(member2);

        Member result = memoryMemberRepository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        memoryMemberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring22");
        memoryMemberRepository.save(member2);

        List<Member> result = memoryMemberRepository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}
