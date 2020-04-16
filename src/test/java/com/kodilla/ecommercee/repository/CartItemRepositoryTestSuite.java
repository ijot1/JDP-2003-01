package com.kodilla.ecommercee.repository;

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
public class CartItemRepositoryTestSuite {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Test
    public void testSaveCartItem() {
        //Given
        CartItem cartItem = CartItem.builder()
                .addedDate(LocalDate.of(2003, 12, 3))
                .priceDate(LocalDate.of(2005, 12, 4))
                .products(new ArrayList<>())
                .productName("cheese")
                .build();

        //When
        CartItem savedCartItem = cartItemRepository.save(cartItem);
        Long id = savedCartItem.getId();
        //Then
        assertNotEquals((Object) 0L, id);
        //Cleanup
        cartItemRepository.deleteById(id);
    }

    @Test
    public void testFindAllCartItem() {
        //Given
        CartItem cartItem1 = CartItem.builder()
                .addedDate(LocalDate.of(2003, 12, 3))
                .priceDate(LocalDate.of(2005, 12, 4))
                .products(new ArrayList<>())
                .productName("cheese")
                .build();
        CartItem cartItem2 = CartItem.builder()
                .addedDate(LocalDate.of(2003, 12, 3))
                .priceDate(LocalDate.of(2005, 12, 4))
                .products(new ArrayList<>())
                .productName("cheese")
                .build();
        CartItem cartItem3 = CartItem.builder()
                .addedDate(LocalDate.of(2003, 12, 3))
                .priceDate(LocalDate.of(2005, 12, 4))
                .products(new ArrayList<>())
                .productName("cheese")
                .build();

        //When
        Long id1 = cartItemRepository.save(cartItem1).getId();
        Long id2 = cartItemRepository.save(cartItem2).getId();
        Long id3 = cartItemRepository.save(cartItem3).getId();
        int count = cartItemRepository.findAll().size();
        //Then
        assertEquals(3, count);
        //Cleanup
        cartItemRepository.deleteById(id1);
        cartItemRepository.deleteById(id2);
        cartItemRepository.deleteById(id3);
    }

    @Test
    public void testFindByIdCartItem() {
        //Given
        CartItem cartItem = CartItem.builder()
                .addedDate(LocalDate.of(2003, 12, 3))
                .priceDate(LocalDate.of(2005, 12, 4))
                .products(new ArrayList<>())
                .productName("cheese")
                .build();

        //When
        CartItem savedCartItem = cartItemRepository.save(cartItem);
        Long id = savedCartItem.getId();
        Optional<CartItem> readCartItem = cartItemRepository.findById(id);

        //Then
        assertTrue(readCartItem.isPresent());
        //CleanUp
        cartItemRepository.deleteById(id);
    }

    @Test
    public void testDeleteByIdCartItem() {
        //Given
        CartItem cartItem = CartItem.builder()
                .addedDate(LocalDate.of(2003, 12, 3))
                .priceDate(LocalDate.of(2005, 12, 4))
                .products(new ArrayList<>())
                .productName("cheese")
                .build();

        //When
        CartItem savedCartItem = cartItemRepository.save(cartItem);
        Long id = savedCartItem.getId();
        cartItemRepository.deleteById(id);

        //Then
        assertEquals(0, cartItemRepository.findAll().size());

    }
}
