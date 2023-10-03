package com.cjw.chatting.service.message;

import com.cjw.chatting.dto.eventrecord.payload.EventPayload;
import com.cjw.chatting.dto.exception.BasicException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.cjw.chatting.util.constant.TopicConstant.TOPIC_SAVE_MESSAGE;

@Service
@RequiredArgsConstructor
public class MessageEventService {
    public void sendSaveMessageRecord(EventPayload payload) {
    }
}
