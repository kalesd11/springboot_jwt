package com.example.jwtapp.service;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.jwtapp.entity.User;
import com.example.jwtapp.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findByUsername(username).orElse(null);
        // User u = new User();
        // u.setUsername("isha");
        // u.setPassword("$2a$10$5UPXbT3EeUecOmmUVhJ8FOCjLzVqTiUsMpnrO.ZE1J0ElGjPeIGkq");
        // u.setRoles("Admin");
        String roles = u.getRoles() == null ? "" : u.getRoles();
        return new org.springframework.security.core.userdetails.User(
                u.getUsername(),
                u.getPassword(),
                Arrays.stream(roles.split(","))
                        .filter(s -> !s.isBlank())
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList()));
                
    }
}
