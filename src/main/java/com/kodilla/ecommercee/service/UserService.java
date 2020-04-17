package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDto> getUsers() {
        return userMapper.mapToUserDtoList(userRepository.findAll());
    }

    public UserDto getUserById(final Long id) {
        Optional<User> user = userRepository.findById(id);
        return userMapper.mapToUserDto(user.orElse(null));
    }

    public void deleteUser(final Long id) {
        userRepository.deleteById(id);
    }

    public UserDto createUser(final UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userDto.setId(null);
        return userMapper.mapToUserDto(userRepository.save(user));
    }

    public UserDto updateUser(final UserDto userDto) {
        userRepository.findById(userDto.getId());
        return userMapper.mapToUserDto(userRepository.save(userMapper.mapToUser(userDto)));
    }
}