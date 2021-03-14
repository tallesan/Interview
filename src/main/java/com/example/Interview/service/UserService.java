package com.example.Interview.service;

import com.example.Interview.dao.UserDao;
import com.example.Interview.model.Users;

public interface UserService {
    public Users getUsersById(Long id);
    public Users getUsersByEmail(String email);
    public long saveUsers(UserDao users);
    public void updateUsers(Long id, UserDao users);
    public void deleteUsers(Long id);

}
