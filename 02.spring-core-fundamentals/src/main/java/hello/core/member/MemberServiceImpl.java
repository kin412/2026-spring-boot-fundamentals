package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    //AppConfig를 통한 di 주입
    //private MemberRepository memberRepository;

    //생성자 주입을 통해 생성시점에 딱한번 주입 되므로 final로 무결성을 지킬수 있음.
    private final MemberRepository memberRepository;

    //생성자 주입
    @Autowired //컴포넌트 스캔을 이용한 자동 주입. ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
