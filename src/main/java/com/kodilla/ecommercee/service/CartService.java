package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart saveCart(final Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart getCartById(final Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    public void deleteCart(final Long id) {
        cartRepository.deleteById(id);
    }
}
