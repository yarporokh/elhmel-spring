package com.example.elhmel.service.Impl;

import com.example.elhmel.model.User;
import com.example.elhmel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomOidcUserServiceImpl extends OidcUserService {

    private final UserRepository userRepository;

    @Autowired
    public CustomOidcUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);

        try {
            return processOidcUser(userRequest, oidcUser);
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OidcUser processOidcUser(OidcUserRequest userRequest, OidcUser oidcUser) {
        Map<String, Object> authUserInfo = oidcUser.getAttributes();
        System.out.println(authUserInfo);

        User user = userRepository.findByUsername((String) authUserInfo.get("email"));
        if (user == null) {
            User newUser = User.builder()
                    .username((String) authUserInfo.get("email"))
                    .password(new BCryptPasswordEncoder(11).encode("password"))
                    .role("ADMIN")
                    .build();
            userRepository.save(newUser);
        }

        return oidcUser;
    }
}
