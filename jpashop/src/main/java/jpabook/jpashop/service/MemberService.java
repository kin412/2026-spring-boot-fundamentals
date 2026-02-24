package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@Transactional // jpa는 트랜잭션 안에서
@Transactional(readOnly = true) // @Transactional이 없는 메소드에선 이옵션이 먹히고, join같이 따로 선언한건 더자세하니까 그게 먹히고
//@AllArgsConstructor - 생성자 주입을 만들어주는 lombok 어노테이션
@RequiredArgsConstructor // final이 붙은 필드만 가지고 생성자를 만들어줌.
public class MemberService {

    //@Autowired - 이렇게 해도되지만 최근 추세는 테스트를 위해서 생성자 인젝션 하는경우가 대부분임
    private final MemberRepository memberRepository;

    //setter 인젝션 - 테스트할때도 그냥 주입하고 싶은거 주입하면됨.
    //단점 - 누군가 잘못써버리면 문제를 발생시킬수도 있음.
    /*@Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

    /*생성자 인젝션 - 어차피 memberRepository는 우리가 중간에 바꿀일이 없으므로
    생성할때 그냥 주입받고 끝내면됨.
    그리고 테스트시 객체생성하면서 mock주입하기도 편함 ex) new MemberService(mock)
    @AllArgsConstructor와 @RequiredArgsConstructor 둘다 이걸 만들어주지만
    @RequiredArgsConstructor 가 final 필드만 만들어주므로 이걸 씀*/
    /*@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

    /*
            회원 가입
             */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    //@Transactional(readOnly = true)// 조회에서는 readOnly를 해주면 최적화가 됨
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }


    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

}
