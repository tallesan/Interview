package com.example.Interview.service.Impl;

import com.example.Interview.dao.UserDao;
import com.example.Interview.model.Users;
import com.example.Interview.repository.UserRepository;
import com.example.Interview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public Users getUsersById(Long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public Users getUsersByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public long saveUsers(UserDao users) {
        Users user = new Users();
        user.setEmail(users.getEmail());
        user.setFirstName(users.getFirstName());
        user.setLastName(users.getLastName());
        user.setPassword(passwordEncoder.encode(users.getPassword()));
        return userRepository.saveUser(user);
    }

    public List<Users> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public void updateUsers(Long id, UserDao userUpdate) {
        Users user = userRepository.getUserById(id);
        user.setFirstName(userUpdate.getFirstName());
        user.setLastName(userUpdate.getLastName());
        user.setEmail(userUpdate.getEmail());
        user.setPassword(passwordEncoder.encode(userUpdate.getPassword()));
//        userRepository.saveUser(user);
        userRepository.updateUser(user);
    }

    @Override
    public void deleteUsers(Long id) {
        userRepository.deleteUser(id);
    }
}
