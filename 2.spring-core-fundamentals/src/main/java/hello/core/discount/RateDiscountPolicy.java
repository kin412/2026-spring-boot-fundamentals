package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscountPolicy") - 이렇게 직접 써줘도 되는데 직접 써줄 경우 안에 문자열이 잘못되어도 컴파일때는 에러없이 넘어갈 수도 있음.
// 잘못되었을때 컴파일때 걸러내는게 가장 좋기 때문에 그렇게 하기 위해 어노테이션을 만들어서 아래와 같이 직접 지정가능
//@MainDiscountPolicy 이러면 오타나면 컴파일 에러가 나서 잡기 가능
@Primary //제일 깨끗해서 많이 씀  ex) 메인데이터 베이스, 보조 데이터 베이스
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * discountPercent / 100;
        }else{
            return 0;
        }
    }
}
