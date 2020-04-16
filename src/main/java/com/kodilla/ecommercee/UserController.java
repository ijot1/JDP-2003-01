package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.EntityNotFoundException;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("v1/ecommercee/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping(value = "getUsers")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(value = "getUser")
    public UserDto getUser(@RequestParam("userId") Long userId) {
        return userMapper.mapToUserDto(userService.getUser(userId).orElseThrow(() -> new EntityNotFoundException(User.class, userId)));
    }

    @PostMapping(value = "createUser", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        userService.saveUser(userMapper.mapToUser(userDto));
    }

    @PutMapping(value = "updateUser", consumes = APPLICATION_JSON_VALUE)
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @DeleteMapping(value = "deleteUser")
    public void deleteUser(@RequestParam("userId") Long userId) {
        userService.deleteUser(userId);
    }
}
