package com.example.Interview.service.Impl;

import com.example.Interview.dao.UserDao;
import com.example.Interview.model.Users;
import com.example.Interview.repository.UsersRepository;
import com.example.Interview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder passwordEncoder, UsersRepository usersRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
    }

    @Override
    public Users getUsersById(Long id) {
        return usersRepository.findById(id).orElseThrow();
    }

    @Override
    public Users getUsersByEmail(String email) {
        return usersRepository.findByEmail(email).orElseThrow();
    }

    @Override
    public void saveUsers(UserDao users) {
        Users user = new Users();
        user.setEmail(users.getEmail());
        user.setFirstName(users.getFirstName());
        user.setLastName(users.getLastName());
        user.setPassword(passwordEncoder.encode(users.getPassword()));
        usersRepository.save(user);
    }

    @Override
    public void updateUsers(Long id, Users users) {
    }

    @Override
    public void deleteUsers(Long id) {
        usersRepository.deleteById(id);
    }
}
