package com.cjw.chatting.config;

import com.sun.security.auth.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class WebSocketHandshakeHandler extends DefaultHandshakeHandler {
    private static AtomicInteger num = new AtomicInteger(1);
    /**
     * 추후 Spring Security 적용 후 유저 정보로 넣기
     */
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        int id = num.getAndIncrement();
        log.info(String.valueOf(id));
        return new UserPrincipal(String.valueOf(id));
    }
}
