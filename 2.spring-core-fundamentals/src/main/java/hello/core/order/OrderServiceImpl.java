package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor //lombok의 생성자 주입 어노테이션. @primary를 사용하면 인터페이스의 구현체가 여러개여도 @RequiredArgsConstructor 사용가능
public class OrderServiceImpl implements OrderService {

    //ocp, dip 위반.
    //private final MemberRepository memberRepository =  new MemoryMemberRepository();

    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    //Appconfig를 통한 생성자 주입
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /* 이 생성자 주입을 롬복의 @RequiredArgsConstructor 가 해준다.
    실제 .class 파일을 열어보면 이코드가 추가되어있다.
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/

    /* @Autowired 는 타입 매칭을 시도한 후에 이름 매칭을 한다.
    매개변수가 DiscountPolicy discountPolicy 이렇게 되어있고, DiscountPolicy를 상속한 클래스가 FixDiscountPolicy 하나밖에 없다면,
    혹은 @Component를 통해 빈에 등록한 클래스가 FixDiscountPolicy 하나밖에 없다면 자동으로 FixDiscountPolicy를 주입한다.
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    하지만매개변수 DiscountPolicy discountPolicy 이렇게 해두었는데, DiscountPolicy를 상속한 클래스가 FixDiscountPolicy, RateDiscountPolicy
    두개라면 타입 매칭에서 클래스 빈이 두개가 나와서 에러가 뜨므로 매개변수명을 fixDiscountPolicy로 맞추면 알아서 자동주입을 한다.
    여기서 클래스를 빈으로 등록할때 앞의 대문자를 소문자로 만들기 때문에 소문자로 적는다.
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy fixDiscountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    아니면 @Qualifier를 사용해 다음과 같이 구분해 줄수도 있다. 해당 구현체에 @Qualifier("mainDiscountPolicy") 를 해주어야 한다.
    @Qualifier는 빈 등록 시에도 써줄수 있다.
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    아니면 @MainDiscountPolicy 이런식으로 어노테이션을 직접 만들어서 지정할 수도 있음
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    다 생략하고 우선권을 지정하는게 @Primary
    하지만 @Primary 와 @Qualifier 둘다 있다면 @Qualifier가 우선순위를 가진다. 스프링은 무조건 자동보단 수동이 우선권을 가진다.

     */

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);

    }

    //테스트용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
