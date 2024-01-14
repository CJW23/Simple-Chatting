package com.cjw.chatting.config.security;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SimpleChattingAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //받은 토큰 Expire 검증 후 인증 여부 결정
        return new SimpleChattingAuthenticationToken(null, null, null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (SimpleChattingAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
