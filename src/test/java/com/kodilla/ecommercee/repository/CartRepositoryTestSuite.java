package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartRepositoryTestSuite {

    @Autowired
    private CartRepository cartRepository;

    @Test
    public void testSaveCartWithCartItem() {
        //Given
        CartItem cartItem1 = CartItem.builder()
                .addedDate(LocalDate.of(2003, 12, 3))
                .priceDate(LocalDate.of(2005, 12, 4))
                .productName("cheese")
                .products(new ArrayList<>())
                .build();
        CartItem cartItem2 = CartItem.builder()
                .addedDate(LocalDate.of(2003, 12, 3))
                .priceDate(LocalDate.of(2005, 12, 4))
                .productName("cheese")
                .products(new ArrayList<>())
                .build();
        CartItem cartItem3 = CartItem.builder()
                .addedDate(LocalDate.of(2003, 12, 3))
                .priceDate(LocalDate.of(2005, 12, 4))
                .productName("cheese")
                .products(new ArrayList<>())
                .build();
        Cart cart = Cart.builder()
                .cartDate(LocalDate.of(2003, 12, 7))
                .cartItem(new ArrayList<>())
                .build();
        cart.getCartItem().add(cartItem1);
        cart.getCartItem().add(cartItem2);
        cart.getCartItem().add(cartItem3);
        cartItem1.setCart(cart);
        cartItem2.setCart(cart);
        cartItem3.setCart(cart);

        //When
        Cart savedCart = cartRepository.save(cart);
        Long id = savedCart.getId();
        //Then
        assertNotEquals((Object) 0L, id);
        //CleanUp
        cartRepository.deleteById(id);
    }

    @Test
    public void testSaveCart() {
        //Given
        Cart cart = Cart.builder()
                .cartDate(LocalDate.of(2003, 12, 7))
                .cartItem(new ArrayList<>())
                .build();

        //When
        Cart savedCart = cartRepository.save(cart);
        Long id = savedCart.getId();
        //Then
        assertNotEquals((Object) 0L, id);
        //CleanUp
        cartRepository.deleteById(id);
    }

    @Test
    public void testFindAllCarts() {
        //Given
        Cart cart1 = Cart.builder()
                .cartDate(LocalDate.of(2003, 12, 7))
                .cartItem(new ArrayList<>())
                .build();
        Cart cart2 = Cart.builder()
                .cartDate(LocalDate.of(2003, 12, 7))
                .cartItem(new ArrayList<>())
                .build();
        Cart cart3 = Cart.builder()
                .cartDate(LocalDate.of(2003, 12, 7))
                .cartItem(new ArrayList<>())
                .build();

        //When
        Long id1 = cartRepository.save(cart1).getId();
        Long id2 = cartRepository.save(cart2).getId();
        Long id3 = cartRepository.save(cart3).getId();
        int count = cartRepository.findAll().size();
        //Then
        assertEquals(3, count);
        //Cleanup
        cartRepository.deleteById(id1);
        cartRepository.deleteById(id2);
        cartRepository.deleteById(id3);
    }

    @Test
    public void testFindByIdCart() {
        //Given
        Cart cart = Cart.builder()
                .cartDate(LocalDate.of(2003, 12, 7))
                .cartItem(new ArrayList<>())
                .build();

        //When
        Cart savedCart = cartRepository.save(cart);
        Long id = savedCart.getId();
        Optional<Cart> readCart = cartRepository.findById(id);

        //Then
        assertTrue(readCart.isPresent());
        //ClanUp
        cartRepository.deleteById(id);
    }

    @Test
    public void testDeleteByIdCart() {
        //Given
        Cart cart = Cart.builder()
                .cartDate(LocalDate.of(2003, 12, 7))
                .cartItem(new ArrayList<>())
                .build();

        //When
        Cart savedCart = cartRepository.save(cart);
        Long id = savedCart.getId();
        cartRepository.deleteById(id);

        //Then
        assertEquals(0, cartRepository.findAll().size());
    }
}
