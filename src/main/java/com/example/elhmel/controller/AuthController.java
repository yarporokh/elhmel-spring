package com.example.elhmel.controller;

import com.example.elhmel.model.User;
import com.example.elhmel.repository.UserRepository;
import com.example.elhmel.service.Impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    private final UserDetailsServiceImpl userDetailsService;
    private final UserRepository userRepository;

    @Autowired
    public AuthController(UserDetailsServiceImpl userDetailsService, UserRepository userRepository) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String getRegistrationPage() {
        return "registration";
    }

    @PostMapping("/register")
    public String registration(@RequestParam("username") String username,
                               @RequestParam("password") String password
    ) {
        User user = User.builder()
                .username(username)
                .password(password)
                .build();
        userDetailsService.registerUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

}
