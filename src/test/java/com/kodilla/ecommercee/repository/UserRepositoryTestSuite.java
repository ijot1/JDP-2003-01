package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() {
        //Given
        User user = User.builder()
                .addressId(1L)
                .firstName("test_firstName")
                .lastName("test_lastName")
                .login("test_login")
                .password("test_password")
                .email("test_email")
                .orders(new ArrayList<>())
                .addresses(new ArrayList<>())
                .build();

        //When
        User savedUser = userRepository.save(user);
        Long id = savedUser.getId();

        //Then
        Assert.assertNotEquals((Object) 0L, id);

        //CleanUp
        userRepository.deleteById(id);
        }

    @Test
    public void testFindByIdUser() {
        //Given
        User user = User.builder()
                .addressId(1L)
                .firstName("test_firstName#1")
                .lastName("test_lastName#1")
                .login("test_login#1")
                .password("test_password#1")
                .email("test_email#1")
                .orders(new ArrayList<>())
                .addresses(new ArrayList<>())
                .build();

        //When
        User savedUser = userRepository.save(user);
        Long id = savedUser.getId();
        Optional<User> readOpUser = userRepository.findById(id);

        //Then
        Assert.assertTrue(readOpUser.isPresent());

        //CleanUp
        userRepository.deleteById(id);
    }

    @Test
    public void testFindAll() {
        //Given
        User user1 = User.builder()
                .addressId(1L)
                .firstName("test_firstName#1")
                .lastName("test_lastName#1")
                .login("test_login#1")
                .password("test_password#1")
                .email("test_email#1")
                .orders(new ArrayList<>())
                .addresses(new ArrayList<>())
                .build();

        User user2 = User.builder()
                .addressId(1L)
                .firstName("test_firstName#2")
                .lastName("test_lastName#2")
                .login("test_login#2")
                .password("test_password#2")
                .email("test_email#2")
                .orders(new ArrayList<>())
                .addresses(new ArrayList<>())
                .build();

        User user3 = User.builder()
                .addressId(1L)
                .firstName("test_firstName#3")
                .lastName("test_lastName#3")
                .login("test_login#3")
                .password("test_password#3")
                .email("test_email#3")
                .orders(new ArrayList<>())
                .addresses(new ArrayList<>())
                .build();

        //When
        User savedUser1 = userRepository.save(user1);
        User savedUser2 = userRepository.save(user2);
        User savedUser3 = userRepository.save(user3);
        Long id1 = savedUser1.getId();
        Long id2 = savedUser2.getId();
        Long id3 = savedUser3.getId();

        //Then
        Assert.assertEquals(3, userRepository.findAll().size());

        //CleanUp
        userRepository.deleteById(id1);
        userRepository.deleteById(id2);
        userRepository.deleteById(id3);
    }

    @Test
    public void testDeleteByIdUser() {
        //Given
        User user = User.builder()
                .addressId(1L)
                .firstName("test_firstName#1")
                .lastName("test_lastName#1")
                .login("test_login#1")
                .password("test_password#1")
                .email("test_email#1")
                .orders(new ArrayList<>())
                .addresses(new ArrayList<>())
                .build();

        //When
        User savedUser = userRepository.save(user);
        Long id = savedUser.getId();
        userRepository.deleteById(id);

        //Then
        Assert.assertEquals(0, userRepository.findAll().size());

    }

}

