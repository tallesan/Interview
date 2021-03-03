package com.example.Interview.service.Impl;

import com.example.Interview.repository.UsersRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserAuthService implements UserDetailsService {
    private final UsersRepository usersRepository;

    public UserAuthService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usersRepository.findByEmail(email)
                .map(users -> new User(
                        users.getEmail(),
                        users.getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority("USER"))
                )).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }
}
