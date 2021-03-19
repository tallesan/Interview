package com.example.Interview.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDao {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
