package com.cjw.chatting.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfiguration {
    private final ChattingOAuth2UserService chattingOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(a ->
                        a.requestMatchers("/test").authenticated()
                                .anyRequest().permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                //oauth2 체크전에 accessToken 있는지 체크
                .addFilterBefore(this.createFilter(), OAuth2LoginAuthenticationFilter.class)
                .oauth2Login(config -> {
                    config
                            .loginPage("/login")
                            .userInfoEndpoint(userInfoEndpointConfig ->
                                    userInfoEndpointConfig.userService(chattingOAuth2UserService))
                            .successHandler(successHandler())
                    ;  //커스텀 userService 등록

                });

        return http.build();
    }

    private SimpleChattingAuthenticationFilter createFilter() {
        return new SimpleChattingAuthenticationFilter("/test");
    }

    public AuthenticationSuccessHandler successHandler() {
        return ((request, response, authentication) -> {
            //response.sendRedirect("https://naver.com");
        });
    }
}
