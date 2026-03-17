package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        //만약 필드주입을 했을 경우 이렇게 내가 직접 new 해서 넣지 못한다.
        //setter 주입을 했을 경우 내가 직접 넣을 수는 있으나, setter의 경우 매개변수를 넣지 않아도 동작하기 때문에 필수조건이 선택조건이 되버린다.
        //정해진 시나리오를 벗어나게 된다.
        //그래서 필수조건을 지정하면서도, 테스트할때 직접 넣을 수 있는 생성자 주입을 대세로 쓴다.
        //그리고 생성자 주입을 쓰면 final 키워드를 쓸수 있기때문에 초기에 값을 정해주고 나면 변하지 않기때문에 불변성을 유지할 수 있다.
        //final키워드를 쓸 경우 한번은 꼭 넣어주고, 이후에는 변하지 않기 때문에 꼭 필요한 값을 안넣어준다면 에러가 나고, 이후에 변하지도 않는다.
        //가끔 옵션이 필요할 경우 setter주입을 사용하는 경우도 있으며, 필드주입은 사용하지 않는게 좋다.
        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());


        Order order = orderService.createOrder(1L, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }


}