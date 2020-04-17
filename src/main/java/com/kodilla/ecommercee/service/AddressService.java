package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Address;
import com.kodilla.ecommercee.domain.AddressDto;
import com.kodilla.ecommercee.domain.EntityNotFoundException;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.mapper.AddressMapper;
import com.kodilla.ecommercee.repository.AddressRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;

    public AddressDto create(AddressDto addressDto) {
        User user = userRepository.findById(addressDto.getUserId()).orElseThrow(() -> new EntityNotFoundException(User.class, addressDto.getUserId()));
        addressDto.setId(null);
        Address address = addressMapper.map(addressDto, user);
        return addressMapper.mapToDto(addressRepository.save(address));
    }

    public AddressDto update(AddressDto addressDto) {
        addressRepository.findById(addressDto.getId()).orElseThrow(() -> new EntityNotFoundException(Address.class, addressDto.getId()));
        User user = userRepository.findById(addressDto.getUserId()).orElseThrow(() -> new EntityNotFoundException(User.class, addressDto.getUserId()));
        return addressMapper.mapToDto(addressRepository.save(addressMapper.map(addressDto, user)));
    }

    public List<AddressDto> getAddresses() {
        return addressMapper.mapToDtoList(addressRepository.findAll());
    }

    public AddressDto getAddress(final Long id) {
        Optional<Address> address = addressRepository.findById(id);
        return addressMapper.mapToDto(address.orElseThrow(() -> new EntityNotFoundException(Address.class, id)));
    }

    public void deleteAddress(final Long addressId) {
        addressRepository.findById(addressId).orElseThrow(() -> new EntityNotFoundException(Address.class, addressId));
        addressRepository.deleteById(addressId);
    }
}
