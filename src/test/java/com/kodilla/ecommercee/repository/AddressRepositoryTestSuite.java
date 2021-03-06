package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressRepositoryTestSuite {


    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testAddressWithOrder() {

        Order order = Order.builder()
                .address(new Address())
                .orderDate(LocalDate.now())
                .deliveryDate(LocalDate.now().plusDays(1))
                .orderStatus(OrderStatus.ORDER_CANCELED)
                .deliveryType(DeliveryType.TO_HOME)
                .paymentType(PaymentType.PAYU)
                .build();

        Address address = Address.builder()
                .city("Radom")
                .houseNumber("13")
                .phoneNumber("000")
                .street("Dobre")
                .zipCode("31313213")
                .build();

        Address saveAddress = addressRepository.save(address);
        order.setAddress(address);
        Long addressId = saveAddress.getId();
        Order orderSaved = orderRepository.save(order);
        Long addrIdFromOrder = orderSaved.getAddress().getId();

        Assert.assertSame(addressId, addrIdFromOrder);
        orderRepository.deleteById(orderSaved.getId());
        addressRepository.deleteById(addressId);
    }


    @Test
    public void testSaveAddress() {
        Address address = Address.builder()
                .city("Radom")
                .houseNumber("13")
                .phoneNumber("000")
                .street("Dobre")
                .zipCode("31313213")
                .build();

        Address saveAddress = addressRepository.save(address);
        Long addressId = saveAddress.getId();

        Assert.assertEquals((Object) 1L, addressId);
        addressRepository.deleteById(addressId);
    }


    @Test
    public void testGetAllAddresses() {
        Address address1 = Address.builder()
                .id(11L)
                .city("3")
                .houseNumber("13")
                .phoneNumber("000")
                .street("Dobre")
                .zipCode("31313213")
                .build();

        Address address2 = Address.builder()
                .id(21L)
                .city("2")
                .houseNumber("13")
                .phoneNumber("000")
                .street("Dobre")
                .zipCode("31313213")
                .build();

        addressRepository.save(address1);
        addressRepository.save(address2);
        int countOfAddresses = addressRepository.findAll().size();

        Assert.assertEquals(2, countOfAddresses);
    }


    @Test
    public void testGetAddressById() {
        Address address = Address.builder()
                .city("Radom")
                .houseNumber("13")
                .phoneNumber("000")
                .street("Dobre")
                .zipCode("31313213")
                .build();

        Address saveAddress = addressRepository.save(address);
        Long addressId = saveAddress.getId();

        Optional<Address> optionalAddress = addressRepository.findById(addressId);
        Assert.assertTrue(optionalAddress.isPresent());
        addressRepository.deleteById(addressId);
    }
}
