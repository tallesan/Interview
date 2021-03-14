package com.example.Interview.controllers.api;

import com.example.Interview.Dto.UserDto;
import com.example.Interview.dao.UserDao;
import com.example.Interview.model.Users;
import com.example.Interview.service.Impl.UserServiceImpl;
import com.example.Interview.utils.DtoConvert;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserApiController {
    private final DtoConvert dtoConvert;
    private final UserServiceImpl userService;

    public UserApiController(DtoConvert dtoConvert, UserServiceImpl userService) {
        this.dtoConvert = dtoConvert;
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        return dtoConvert.userAllUserDto();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable(value = "id") Long id) {
        return dtoConvert.userToUserDto(id);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> createUser(@RequestBody UserDao user) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        user.setId(userService.saveUsers(user));
        return new ResponseEntity<>(user, new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> updateUser(@RequestBody UserDao user) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.updateUsers(user.getId(), user);
        return new ResponseEntity<>(user, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        Users user = userService.getUsersById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.deleteUsers(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
