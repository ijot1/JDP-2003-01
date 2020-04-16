package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    public Order mapToOrder(final OrderDto orderDto, final Cart cart, final Address address, final User user) {
        return Order.builder()
                .id(orderDto.getId())
                .cart(cart)
                .address(address)
                .user(user)
                .orderDate(orderDto.getOrderDate())
                .deliveryDate(orderDto.getDeliveryDate())
                .deliveryType(orderDto.getDeliveryType())
                .paymentType(orderDto.getPaymentType())
                .orderStatus(orderDto.getOrderStatus())
                .build();
    }

    public OrderDto mapToOrderDto(final Order order) {
        OrderDto.builder()
                .cartId(order.getCart().getId())
                .build();

        return new OrderDto(
                order.getId(),
                order.getCart().getId(),
                order.getUser().getId(),
                order.getAddress().getId(),
                order.getOrderDate(),
                order.getDeliveryType(),
                order.getPaymentType(),
                order.getOrderStatus()
        );
    }

    public List<OrderDto> mapToOrderDtoList(final List<Order> orderList) {
        return orderList.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }
}