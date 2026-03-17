package com.kin.springboot2026;

import com.kin.springboot2026.aop.TimeTraceAop;
import com.kin.springboot2026.repository.*;
import com.kin.springboot2026.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration //스프링이 뜰때 이 설정 파일을 읽음
public class SpringConfig {
    
    //Controller는 어쩔수 없음

   /* jdbc 방식

    private DataSource dataSource;

    @Autowired //datasource는 application.properties 파일의 db정보를 바탕으로 이미 컨테이너에서 객체로 가지고 있음.
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

   /* jpa 방식

    private EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }*/

    /*
    @Bean // memberService()함수의 리턴값을 bean으로 등록
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
     */

    /*@Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository(); //메모리 방식
        //return new JdbcMemberRepository(dataSource); //model1 방식 pstmt, rs
        //return new JdbcTemplateMemberRepository(dataSource); // template 방식
        //return new JpaMemberRepository(em); // jpa 방식
        //spring data jpa 방식은 필요없음.
    }*/

    //spring data jpa 방식
    private final MemberRepository memberRepository;

    @Bean // memberService()함수의 리턴값을 bean으로 등록
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    // config의 생성자니까 이거부터 실행.해서 필드값을 받음.
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*@Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }*/

}
