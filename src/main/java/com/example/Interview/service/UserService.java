package com.example.Interview.service;

import com.example.Interview.dao.UserDao;
import com.example.Interview.model.Users;

public interface UserService {
    public Users getUsersById(Long id);
    public Users getUsersByEmail(String email);
    public void saveUsers(UserDao users);
    public void updateUsers(Long id, Users users);
    public void deleteUsers(Long id);

}
