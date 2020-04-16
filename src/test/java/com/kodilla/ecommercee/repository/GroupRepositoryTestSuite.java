package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupRepositoryTestSuite {

    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void testSaveProductWithGroup() {
        //Given
        Product product1 = Product.builder()
                .quantity(1)
                .price(new BigDecimal(3))
                .description("fast")
                .productName("passat")
                .build();
        Product product2 = Product.builder()
                .quantity(1)
                .price(new BigDecimal(3))
                .description("fast")
                .productName("passat")
                .build();
        Product product3 = Product.builder()
                .quantity(1)
                .price(new BigDecimal(3))
                .description("fast")
                .productName("passat")
                .build();
        Group group = Group.builder()
                .products(new ArrayList<>())
                .description("cars")
                .groupName("cars")
                .build();
        group.addProduct(product1);
        group.addProduct(product2);
        group.addProduct(product3);
        product1.setGroup(group);
        product2.setGroup(group);
        product3.setGroup(group);

        //When
        Group savedGroup = groupRepository.save(group);
        Long id = savedGroup.getId();
        //Then
        assertNotEquals((Object) 0L, id);
        //CleanUp
        groupRepository.deleteById(id);
    }

    @Test
    public void testSaveGroup() {
        //Given
        Group group = Group.builder()
                .products(new ArrayList<>())
                .description("cars")
                .groupName("cars")
                .build();

        //When
        Group savedGroup = groupRepository.save(group);
        Long id = savedGroup.getId();
        //Then
        assertNotEquals((Object) 0L, id);
        //CleanUp
        groupRepository.deleteById(id);
    }

    @Test
    public void testFindAllGroups() {
        //Given
        Group group1 = Group.builder()
                .products(new ArrayList<>())
                .description("cars")
                .groupName("cars")
                .build();
        Group group2 = Group.builder()
                .products(new ArrayList<>())
                .description("cars")
                .groupName("cars")
                .build();
        Group group3 = Group.builder()
                .products(new ArrayList<>())
                .description("cars")
                .groupName("cars")
                .build();

        //When
        Long id1 = groupRepository.save(group1).getId();
        Long id2 = groupRepository.save(group2).getId();
        Long id3 = groupRepository.save(group3).getId();
        int count = groupRepository.findAll().size();
        //Then
        assertEquals(3, count);
        //Cleanup
        groupRepository.deleteById(id1);
        groupRepository.deleteById(id2);
        groupRepository.deleteById(id3);
    }

    @Test
    public void testFindByIdGroup() {
        //Given
        Group group = Group.builder()
                .products(new ArrayList<>())
                .description("cars")
                .groupName("cars")
                .build();

        //When
        Group savedGroup = groupRepository.save(group);
        Long id = savedGroup.getId();
        Optional<Group> readGroup = groupRepository.findById(id);

        //Then
        assertTrue(readGroup.isPresent());
        //ClanUp
        groupRepository.deleteById(id);
    }
}
