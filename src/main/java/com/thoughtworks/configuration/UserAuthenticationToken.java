package com.thoughtworks.configuration;

import com.thoughtworks.dto.SecurityUser;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class UserAuthenticationToken extends AbstractAuthenticationToken {

    private String username;

    private String id;

    public UserAuthenticationToken(String username, String id) {
        super(null);
        this.username = username;
        this.id = id;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return id;
    }

    @Override
    public Object getPrincipal() {
        return SecurityUser
                .builder()
                .id(id)
                .username(username)
                .build();
    }
}
