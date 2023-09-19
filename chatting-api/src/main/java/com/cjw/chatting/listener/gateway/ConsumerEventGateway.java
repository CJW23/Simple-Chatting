package com.cjw.chatting.listener.gateway;

import com.cjw.chatting.service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsumerEventGateway {
    private final MessageService messageService;
    private final ObjectMapper mapper;

    public void handleEvent(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        //객체로 변환 후 타입?을 확인해서 분기처리하기 위함인데 지금 당장 필요할까
        try {
            mapper.readValue(record.value(), Object.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        switch (record.topic()) {
            case "save-message":
                //...
        }
    }
}
