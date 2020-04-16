package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.repository.AddressRepository;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final CartRepository cartRepository;

    @Autowired
    public OrderService(OrderMapper orderMapper, OrderRepository orderRepository, UserRepository userRepository, AddressRepository addressRepository, CartRepository cartRepository) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.cartRepository = cartRepository;
    }

    public List<OrderDto> getOrders() {
        return orderMapper.mapToOrderDtoList(orderRepository.findAll());
    }

    public OrderDto getOrderById(final Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return orderMapper.mapToOrderDto(order.orElse(null));
    }

    public void deleteOrder(final Long id) {
        orderRepository.deleteById(id);
    }

    public OrderDto createOrder(final OrderDto orderDto) {
        User user = userRepository.findById(orderDto.getUserId()).orElse(null);
        Address address = addressRepository.findById(orderDto.getAddressId()).orElse(null);
        Cart cart = cartRepository.findById(orderDto.getCartId()).orElse(null);
        Order order = orderMapper.mapToOrder(orderDto, cart, address, user);
        orderDto.setId(null);
        return orderMapper.mapToOrderDto(orderRepository.save(order));
    }

    public OrderDto updateOrder(final OrderDto orderDto) {
        orderRepository.findById(orderDto.getId()).orElse(null);
        User user = userRepository.findById(orderDto.getUserId()).orElse(null);
        Address address = addressRepository.findById(orderDto.getAddressId()).orElse(null);
        Cart cart = cartRepository.findById(orderDto.getCartId()).orElse(null);
        return orderMapper.mapToOrderDto(orderRepository.save(orderMapper.mapToOrder(orderDto, cart, address, user)));
    }
}