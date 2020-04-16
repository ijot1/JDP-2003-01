package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.EcommerceeApplication;
import com.kodilla.ecommercee.domain.CartItem;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommerceeApplication.class)
public class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Test
    public void findByIdTest() {
        //Given
        Group group = new Group();
        Product product = new Product();
        CartItem cartItem = new CartItem();
        group.setGroupName("grupa");
        group.setDescription("opis grupy");
        cartItem.setProductId(1L);
        cartItem.setProductName(product.getProductName());
        cartItem.setPriceDate(LocalDate.now());
        cartItem.setProductCount(100L);
        cartItem.setAddedDate(LocalDate.of(2020,10,10));
        product.setDescription("Test desc");
        product.setPrice(new BigDecimal(199));
        product.setProductName("telewizor");
        product.setQuantity(666);
        product.setGroup(group);
        product.setCartItem(cartItem);
        groupRepository.save(group);
        cartItemRepository.save(cartItem);
        productRepository.save(product);
        //When
        Optional<Product> dbProduct = productRepository.findById(product.getId());
        //Then
        Assert.assertEquals("telewizor", dbProduct.get().getProductName());
        Assert.assertEquals("grupa", dbProduct.get().getGroup().getGroupName());
        Assert.assertEquals(LocalDate.of(2020,10,10),
                dbProduct.get().getCartItem().getAddedDate());
    }
    @Test
    public void findAllTest() {
        //Given
        Product product = new Product();
        Product product1 = new Product();
        Product product2 = new Product();

        product.setDescription("Test desc");
        product.setPrice(new BigDecimal(199));
        product.setProductName("telewizor");
        product.setQuantity(666);

        product1.setDescription("Super mydło");
        product1.setPrice(new BigDecimal(10));
        product1.setProductName("mydło");
        product1.setQuantity(100);

        product2.setDescription("Papier do d");
        product2.setPrice(new BigDecimal(5));
        product2.setProductName("papier toaletowy");
        product2.setQuantity(1);

        productRepository.save(product);
        productRepository.save(product1);
        productRepository.save(product2);
        //When
        List<Product> dbProduct = productRepository.findAll();
        //Then
        for(Product list : dbProduct){
            System.out.println(list.getProductName());
        }
        Assert.assertEquals(3, dbProduct.size());
        Assert.assertEquals("Papier do d", dbProduct.get(2).getDescription());

    }

    @Test
    public void deleteByIdTest() {
        //Given
        Product product = new Product();
        Product product1 = new Product();
        Product product2 = new Product();

        product.setDescription("Test desc");
        product.setPrice(new BigDecimal(199));
        product.setProductName("telewizor");
        product.setQuantity(666);

        product1.setDescription("Super mydło");
        product1.setPrice(new BigDecimal(10));
        product1.setProductName("mydło");
        product1.setQuantity(100);

        product2.setDescription("Papier do d");
        product2.setPrice(new BigDecimal(5));
        product2.setProductName("papier toaletowy");
        product2.setQuantity(1);

        productRepository.save(product);
        productRepository.save(product1);
        productRepository.save(product2);
        //When
        productRepository.deleteById(product1.getId());
        productRepository.deleteById(product.getId());
        productRepository.deleteById(product2.getId());
        List<Product> products = productRepository.findAll();
        //Then
        Assert.assertEquals(0, products.size());

    }
}