package com.example.Interview.utils;

import com.example.Interview.Dto.UserDto;
import com.example.Interview.model.Users;
import com.example.Interview.service.Impl.UserServiceImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoConvert {
    private final UserServiceImpl userService;

    public DtoConvert(UserServiceImpl userService) {
        this.userService = userService;
    }

    public UserDto userToUserDto(Long id) {
        Users users = userService.getUsersById(id);
        return new UserDto(users.getId(), users.getFirstName(), users.getLastName(), users.getEmail());
    }

    public List<UserDto> userAllUserDto() {
        List<Users> usersList = userService.getAllUsers();
        return usersList.stream().map(users -> userToUserDto(users.getId())).collect(Collectors.toList());
    }

}
