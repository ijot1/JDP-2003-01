package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Address;
import com.kodilla.ecommercee.domain.AddressDto;
import com.kodilla.ecommercee.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressMapper {
    public Address map(final AddressDto addressDto, final User user) {
        return Address.builder()
                .id(addressDto.getId())
                .user(user)
                .street(addressDto.getStreet())
                .houseNumber(addressDto.getHouseNumber())
                .zipCode(addressDto.getZipCode())
                .city(addressDto.getCity())
                .phoneNumber(addressDto.getPhoneNumber())
                .build();
    }

    public AddressDto mapToDto(final Address address) {
        return new AddressDto(
                address.getId(),
                address.getUser().getId(),
                address.getStreet(),
                address.getHouseNumber(),
                address.getZipCode(),
                address.getCity(),
                address.getPhoneNumber()
        );
    }

    public List<AddressDto> mapToDtoList(final List<Address> addressList) {
        return addressList.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
