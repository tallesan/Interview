package com.example.Interview.repository;

import com.example.Interview.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Users getUserById(Long id) {
        return jdbcTemplate.query("select * from users where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Users.class)).stream().findAny().orElse(null);
    }

    public Users getUserByEmail(String email) {
        return jdbcTemplate.query("select * from users where email = ?", new Object[]{email}, new BeanPropertyRowMapper<>(Users.class)).stream().findAny().orElse(null);
    }

    public List<Users> getAllUsers() {
        return jdbcTemplate.query("select * from users", new BeanPropertyRowMapper<>(Users.class));
    }

    public long saveUser(Users users) {
        String sql = "insert into users(first_name, last_name, email, password) values (?, ?, ?, ?) RETURNING id";
        return jdbcTemplate.queryForObject(sql, new Object[]{users.getFirstName(), users.getLastName(), users.getEmail(), users.getPassword()}, Long.class);
    }

    public void updateUser(Users users) {
        jdbcTemplate.update("update users set id=?, first_name=?, last_name=?, email=?, password=?", users.getId(), users.getFirstName(), users.getLastName(), users.getEmail(), users.getPassword());
    }

    public void deleteUser(Long id) {
        jdbcTemplate.update("delete from users where id=?", id);
    }

    /**
     * Переделать для Security
     */
    public Optional<Users> findByEmail(String email) {
        return jdbcTemplate.query("select * from users where email = ?", new Object[]{email}, new BeanPropertyRowMapper<>(Users.class)).stream().findAny();
    }
}
