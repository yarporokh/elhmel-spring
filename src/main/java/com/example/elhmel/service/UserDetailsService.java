package com.example.elhmel.service;

import com.example.elhmel.model.User;

public interface UserDetailsService extends org.springframework.security.core.userdetails.UserDetailsService {
    boolean registerUser(User user);
}
