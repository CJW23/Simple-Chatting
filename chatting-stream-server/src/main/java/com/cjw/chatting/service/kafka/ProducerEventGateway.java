package com.cjw.chatting.service.kafka;

import com.cjw.chatting.dto.eventrecord.payload.EventPayload;
import com.cjw.chatting.dto.exception.BasicException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.cjw.chatting.util.constant.TopicConstant.TOPIC_SAVE_MESSAGE;

@Service
@RequiredArgsConstructor
public class ProducerEventGateway {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper;

    public void send(EventPayload payload) {
        String payloadAsSerialization = this.convertPayload(payload);

        switch (payload.getTopic()) {
            //메세지 저장 이벤트
            case TOPIC_SAVE_MESSAGE:
                kafkaTemplate.send(TOPIC_SAVE_MESSAGE, payloadAsSerialization);
                break;
            default:
                throw BasicException.ofBadRequest("존재하지 않는 토픽입니다.");

        }
    }

    private String convertPayload(EventPayload payload) {
        try {
            return this.mapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
