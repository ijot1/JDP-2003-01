package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.EntityNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.repository.CartItemRepository;
import com.kodilla.ecommercee.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CartService {

    @Autowired
    CartMapper cartMapper;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    public List<CartDto> getCarts() {
        return cartMapper.mapToCartDtoList(cartRepository.findAll());
    }

    public CartDto getCart(final Long id) throws EntityNotFoundException {
        Optional<Cart> cart = cartRepository.findById(id);
        return cartMapper.mapToCartDto(cart
                .orElseThrow(() -> new EntityNotFoundException(Cart.class, id)));

    }

    public CartDto createCart(CartDto cartDto) throws EntityNotFoundException {
        Cart cart = cartMapper.mapToCart(cartDto);
        return cartMapper.mapToCartDto(cartRepository.save(cart));
    }

    public CartDto update(CartDto cartDto) throws EntityNotFoundException {
        cartRepository.findById(cartDto.getId()).orElseThrow(() -> new EntityNotFoundException(Cart.class, cartDto.getId()));
        ;

        return cartMapper.mapToCartDto(cartRepository.save(cartMapper.mapToCart(cartDto)));
    }

    public void deleteCart(final Long id) {
        cartRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Cart.class, id));
        cartRepository.deleteById(id);
    }
}
