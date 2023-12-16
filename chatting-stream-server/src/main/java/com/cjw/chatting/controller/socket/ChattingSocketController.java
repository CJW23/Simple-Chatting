package com.cjw.chatting.controller.socket;

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
    //어떻게 요청 없이 지속적으로 전달가능할까

    @MessageMapping("/messages/{channelId}") //메세지 전송 URL
    @SendTo("/topic/channel/{channelId}")  //다른 구독한 유저에게
    public ChattingMessageDto message(@DestinationVariable Long channelId, ChattingMessageDto message) {
        log.info("message : " + message.getContent());
        log.info("channelId : " + channelId);
        //카프카 메세지 저장 이벤트
        this.producerEventGateway.send(EventPayloadSaveMessage.of(channelId, 5L, message));
        //다른 구독자(채널 참여자) 소켓 전송
        //todo https://tech.kakao.com/2020/06/08/websocket-part1/
        //그냥 바로 내보내지 말고 pub/sub 활용?
        //그럼 메세지를 받는 커넥션은 따로 있는건가
        //아니면 여기서 pub/sub을 유지하는가
        //스프링에서 pub/sub 구독하고 있을 때 지속적으로 스프링 자체에서 우베소켓 채널로 메세지 전송하는 방식
        //그렇다면 왜 이렇게 써야할까 굳이
        //API -> 이벤트 속도? 결국 메세지를 받으려면 이벤트를 컨슘하는곳에서 과정이 완료돼야 메세지를 다른 유저에게 전송
        //결국 메세지 전송의 속도 관점보다는 안정성을 부여하고 확장성, 유연성등 장점을 활용
        return message;
    }

    //메세지 받기
    //해당 채널을 구독한 유저에게 다른 유저가 전송한 메세지 전달
    @MessageMapping("/messages/receive/{channelId}")
    public void receiveMessage(@DestinationVariable Long channelId) {
        //레디스 pub/sub 에서 해당 path로 메세지를 전송
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
