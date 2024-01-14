package com.cjw.chatting.config.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;

/**
 * 발급한 JwtToken 검증
 */
public class SimpleChattingAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    protected SimpleChattingAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        //헤더에서 Token 받아오겄지
        String authorization = request.getHeader("Authorization");
        //토큰으로 provider 결정
        return this.getAuthenticationManager().authenticate(new SimpleChattingAuthenticationToken(authorization));
    }
}
