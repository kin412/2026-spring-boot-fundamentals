package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderSimpleQueryDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    //엔티티 매니저로 쿼리 반환 시 반환타입은 엔티티여야 하는데 그때는  매개변수를 Order order로 받아도 되지만
    //dto로 받게 될경우는 다 개개변수로 받아줘야한다.
    /*public OrderSimpleQueryDto(Order order) {
        this.orderId = order.getId();
        this.name = order.getMember().getName(); // lazy 초기화 - 영속성컨텍스트가 db쿼리를 날린다.
        this.orderDate = order.getOrderDate();
        this.orderStatus = order.getStatus();
        this.address = order.getDelivery().getAddress(); // lazy 초기화
    }*/

    //dto 버전
    public OrderSimpleQueryDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address) {
        this.orderId = orderId;
        this.name = name; // lazy 초기화 - 영속성컨텍스트가 db쿼리를 날린다.
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address; // lazy 초기화
    }
}
