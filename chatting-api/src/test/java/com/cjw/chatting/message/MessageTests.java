package com.cjw.chatting.message;

import com.cjw.chatting.service.message.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class MessageTests {
    @Autowired
    private MessageService messageService;

    @Test
    @Transactional
    @Rollback
    void 메세지_저장_테스트() {
        //given
       /* EventPayloadSaveMessage payload = EventPayloadSaveMessage.builder()
                .message("test")
                .channelId()*/
        //when

        //then
    }
}
