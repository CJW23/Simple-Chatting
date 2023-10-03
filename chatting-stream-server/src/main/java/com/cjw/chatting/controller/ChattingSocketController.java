package com.cjw.chatting.controller;

import com.cjw.chatting.dto.ChattingMessageDto;
import com.cjw.chatting.dto.eventrecord.payload.EventPayloadSaveMessage;
import com.cjw.chatting.service.kafka.ProducerEventGateway;
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
public class ChattingSocketController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ProducerEventGateway producerEventGateway;

    @MessageMapping("/messages/{channelId}") //메세지 전송 URL
    @SendTo("/topic/channel/{channelId}")  //다른 구독한 유저에게
    public ChattingMessageDto message(@DestinationVariable Long channelId, ChattingMessageDto message) {
        log.info("message : " + message.getContent());
        log.info("channelId : " + channelId);
        //카프카 메세지 저장 이벤트
        this.producerEventGateway.send(EventPayloadSaveMessage.of(channelId, 5L, message));
        //다른 구독자(채널 참여자) 소켓 전송
        return message;
    }

    /**
     * 다중 서버 운영시 각 서버의 토픽에게 메세지를 전달하는 방법을 생각해봐야함
     * 카프카를 통해 메세지 전송 및 각 서버 소켓 토픽에 메시지를 전송(컨슘)
     *
     */
    @MessageMapping("test/{channelId}/{userName}")
    //@SendToUser("/topic/test")
    public void test(SimpMessageHeaderAccessor headerAccessor, Principal principal, @DestinationVariable String channelId, @DestinationVariable String userName, ChattingMessageDto message) {
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
