package com.cjw.chatting.controller;

import com.cjw.chatting.dto.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ChattingController {
    @MessageMapping("/sendMessage/{channelId}")
    @SendTo("/topic/messages/{channelId}")
    public Message message(@DestinationVariable String channelId, Message message) {
        log.info("message : " + message.getContent());
        log.info("channelId : " + channelId);
        return message;
    }
}
