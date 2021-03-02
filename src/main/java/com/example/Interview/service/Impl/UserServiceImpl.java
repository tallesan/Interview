package com.example.Interview.service.Impl;

import com.example.Interview.model.Users;
import com.example.Interview.repository.UsersRepository;
import com.example.Interview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Users getUsersById(Long id) {
        return usersRepository.findById(id).orElseThrow();
    }

    @Override
    public Users getUsersByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public void saveUsers(Users users) {
        usersRepository.save(users);
    }

    @Override
    public void updateUsers(Long id, Users users) {
    }

    @Override
    public void deleteUsers(Long id) {
        usersRepository.deleteById(id);
    }
}
