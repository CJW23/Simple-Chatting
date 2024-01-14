package com.cjw.chatting.config.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class SimpleChattingAuthenticationToken extends AbstractAuthenticationToken {
    private final Object credential;
    private Object principal;

    public SimpleChattingAuthenticationToken(String accessToken) {
        super(null);
        this.credential = accessToken;
        this.setAuthenticated(false);
    }
    public SimpleChattingAuthenticationToken(Collection<? extends GrantedAuthority> authorities, Object principal, String accessToken) {
        super(authorities);
        this.principal = principal;
        this.credential = accessToken;
        this.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
