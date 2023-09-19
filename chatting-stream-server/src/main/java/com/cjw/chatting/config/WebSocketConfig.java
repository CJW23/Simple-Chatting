package com.cjw.chatting.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //해당 prefix 통해 각 subscribe 판별 (ex: /topic/message/1, /topic/message/2) -> 서로 다른 채널
        config.enableSimpleBroker("/topic", "/user");
        //해당 prefix 로 들어올 경우 메세지 처리 -> @MessageMapping 에서 처리
        config.setApplicationDestinationPrefixes("/simple-chatting");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/ws-simple-chatting")
                .setAllowedOriginPatterns("http://localhost:[*]")
                .setHandshakeHandler(new WebSocketHandshakeHandler())
                .withSockJS();
    }
}