package com.example.Interview.dao;

import lombok.Data;

@Data
public class UserDao {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
