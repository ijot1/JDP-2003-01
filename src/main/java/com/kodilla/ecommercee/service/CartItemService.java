package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartItem;
import com.kodilla.ecommercee.domain.CartItemDto;
import com.kodilla.ecommercee.domain.EntityNotFoundException;
import com.kodilla.ecommercee.mapper.CartItemMapper;
import com.kodilla.ecommercee.repository.CartItemRepository;
import com.kodilla.ecommercee.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    @Autowired
    private CartItemMapper cartItemMapper;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;


    public List<CartItemDto> getCartItems() {
        return cartItemMapper.mapToDtoList(cartItemRepository.findAll());
    }

    public CartItemDto getCartItem(final Long id) throws EntityNotFoundException {
        Optional<CartItem> cartItem = cartItemRepository.findById(id);
        return cartItemMapper.mapToCartItemDto(cartItem
                .orElseThrow(() -> new EntityNotFoundException(Cart.class, id)));
        
    }

    public CartItemDto create(CartItemDto cartItemDto) throws EntityNotFoundException {
        Cart cart = cartRepository.findById(cartItemDto.getCartId())
                .orElseThrow(() -> new EntityNotFoundException(Cart.class, cartItemDto.getCartId()));
        CartItem cartItem = cartItemMapper.mapToCartItem(cartItemDto, cart);
        return cartItemMapper.mapToCartItemDto(cartItemRepository.save(cartItem));
    }

    public CartItemDto update(CartItemDto cartItemDto) throws EntityNotFoundException {
        cartItemRepository.findById(cartItemDto.getId());
        Cart cart = cartRepository.findById(cartItemDto.getCartId())
                .orElseThrow(() -> new EntityNotFoundException(Cart.class, cartItemDto.getCartId()));
        return cartItemMapper.mapToCartItemDto(cartItemRepository.save(cartItemMapper.mapToCartItem(cartItemDto, cart)));
    }

    public void deleteCartItem(final Long cartItemId) {
        cartItemRepository.findById(cartItemId).orElseThrow(() -> new EntityNotFoundException(Cart.class, cartItemId));
        cartItemRepository.deleteById(cartItemId);
    }

}
