package com.example.elhmel.config;


import com.example.elhmel.service.Impl.CustomOidcUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomOidcUserServiceImpl customOidcUserService;

    @Autowired
    public SecurityConfig(CustomOidcUserServiceImpl customOidcUserService) {
        this.customOidcUserService = customOidcUserService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {



        http
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers("/admin/*").hasAuthority("ADMIN")
                .requestMatchers("/*").permitAll()
                .anyRequest().authenticated()
                .and().oauth2Login().userInfoEndpoint().oidcUserService(customOidcUserService).and().loginPage("/login")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/home")
                .and()
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .permitAll()).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .logoutSuccessUrl("/")
                                .permitAll()
                );

        return http.build();
    }
}
