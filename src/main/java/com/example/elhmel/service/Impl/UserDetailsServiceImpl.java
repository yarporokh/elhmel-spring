package com.example.elhmel.service.Impl;

import com.example.elhmel.model.User;
import com.example.elhmel.repository.UserRepository;
import com.example.elhmel.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    @Override
    @Transactional
    public boolean registerUser(User data) {
        User user = User.builder()
                .username(data.getUsername())
                .password(passwordEncoder.encode(data.getPassword()))
                .role("ADMIN")
                .build();
        System.out.println(user);
        userRepository.save(user);
        return true;
    }

}
