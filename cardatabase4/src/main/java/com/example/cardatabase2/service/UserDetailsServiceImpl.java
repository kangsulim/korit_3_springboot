package com.example.cardatabase2.service;

import com.example.cardatabase2.domain.AppUser;
import com.example.cardatabase2.domain.AppUserRepository;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AppUserRepository repository;

    public UserDetailsServiceImpl(AppUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user = repository.findByUsername(username);

        UserBuilder builder = null;

        if (user.isPresent()) {
            AppUser currentUser = user.get();   // AppUser로 받아와서 UserBuilder로 형변환
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(currentUser.getPassword());    // "password"
            builder.roles(currentUser.getRole());           // "USER"
        } else {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        return builder.build();
    }
}
