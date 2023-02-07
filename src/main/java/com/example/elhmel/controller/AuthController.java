package com.example.elhmel.controller;

import com.example.elhmel.model.User;
import com.example.elhmel.repository.UserRepository;
import com.example.elhmel.service.Impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
                               @RequestParam("password") String password,
                               Model model
    ) {
        User user = User.builder()
                .username(username)
                .password(password)
                .build();
        try {
            userDetailsService.registerUser(user);
        } catch (Exception e) {
            model.addAttribute("registrationError", "Account with this email is exist.");
            return "redirect:/register";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/login/oauth2/code/{registrationId}")
    public String oauth2Login(@PathVariable String registrationId,
                              @AuthenticationPrincipal OAuth2User oauth2User) {

        System.out.println(oauth2User);

        return "redirect:/";
    }

}
