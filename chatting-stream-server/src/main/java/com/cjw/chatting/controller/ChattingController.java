package com.cjw.chatting.controller;

import com.cjw.chatting.dto.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ChattingController {
    private final SimpMessagingTemplate messagingTemplate;
    @MessageMapping("/sendMessage/{channelId}")
    @SendTo("/topic/messages/{channelId}")
    public Message message(@DestinationVariable String channelId, Message message) {
        log.info("message : " + message.getContent());
        log.info("channelId : " + channelId);
        return message;
    }

    /**
     * 다중 서버 운영시 각 서버의 토픽에게 메세지를 전달하는 방법을 생각해봐야함
     * 카프카를 통해 메세지 전송 및 각 서버 소켓 토픽에 메시지를 전송(컨슘)
     *
     */
    @MessageMapping("test/{channelId}/{userName}")
    //@SendToUser("/topic/test")
    public void test(SimpMessageHeaderAccessor headerAccessor, Principal principal, @DestinationVariable String channelId, @DestinationVariable String userName, Message message) {
        log.info("header: " + headerAccessor.getSessionId());
        log.info("principal: " + principal);
        log.info("ch : " + channelId);
        log.info("useName: " + userName);
        log.info("mes : " + message.getContent());

        //** 특정 유저에게 전송시 "/user" + userid + destination 로 토픽 전송함 **
        //즉 "/user/1/test" 해당 토픽으로 메세지 전송
        messagingTemplate.convertAndSendToUser(principal.getName(), "/test", message);
        messagingTemplate.convertAndSend("/topic/test", message);
    }
}
