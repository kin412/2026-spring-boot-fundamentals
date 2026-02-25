package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.repository.OrderSimpleQueryDto;
import jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/*  xToOne
    Order
    Order -> Member
    Order -> Delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        //지연로딩과 json의 문제가 발생함. @jsonignore와 하이버네이트(force lazy)로 해결할수도 잇지만 권장하지 않음.
        List<Order> all = orderRepository.findAllByString(new OrderSearch());

        //해법1. 프록시 객체를 직접 호출해 강제 초기화해 값을 넣어준다.
        for (Order order : all) {
            order.getMember().getName(); // Lazy 강제 초기화
            order.getDelivery().getAddress();// Lazy 강제 초기화
        }
        return all;
    }

    //v1의 문제를 dto를 사용하므로써 해결
    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2() {
        //지연로딩이지만 결국 다불러서 json으로 파싱해야 하므로 n+1문제가 발생한다.
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        return orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());

        /*List<SimpleOrderQueryDto> orders = orderRepository.findAllByString(new OrderSearch()).stream()
                .map(o -> new SimpleOrderQueryDto(o))
                .collect(Collectors.toList());*/

        /*return orderRepository.findAllByString(new OrderSearch()).stream()
                .map(o -> new SimpleOrderQueryDto(o))
                .collect(Collectors.toList());*/
    }

    //n+1 문제를 fetch join을 써서 해결 - 김영한 강사님은 이방법을 추천
    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();

        List<SimpleOrderDto> result = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());

        return result;
    }

    //결과를 엔티티로 받아서 dto에 담는게 아닌 바로 dto로 받기
    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> ordersV4() {
       return orderSimpleQueryRepository.findOrderDtos();
    }

    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate; //주문시간
        private OrderStatus orderStatus;
        private Address address;
        public SimpleOrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
        }
    }

}
