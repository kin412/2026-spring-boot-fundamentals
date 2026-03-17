package com.kin.springboot2026.service;

import com.kin.springboot2026.domain.Member;
import com.kin.springboot2026.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
//@Component
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
        회원가입
     **/
    public Long join(Member member) {

        //같은 이름이 있는 중복 회원x
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();

        //aop를 적용함으로써 공통관심사로 분리해냈으므로 핵심로직만 작성해도 됨.
        /*long start = System.currentTimeMillis();

        // 시간은 에러가 터져도 찍혀야 하기때문에
        try {
            //같은 이름이 있는 중복 회원x
            validateDuplicateMember(member);

            memberRepository.save(member);
            return member.getId();
        }finally {
           long finish = System.currentTimeMillis();
           long timeMs = start - finish;
           System.out.println("join timeMs : " + timeMs + "ms");
        }*/


    }

    private void validateDuplicateMember(Member member) {
        //Optional<Member> result = memberRepository.findByName(member.getName());
        //result.orElseGet();
        /*result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });*/
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
      전체 회원 조회
     **/
    public List<Member> findMembers(){

        return memberRepository.findAll();

        /*long start = System.currentTimeMillis();
        try {
            return memberRepository.findAll();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = start - finish;
            System.out.println("findMembers timeMs : " + timeMs + "ms");
        }*/

    }


    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
