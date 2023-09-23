package com.cjw.chatting.listener.gateway;

import com.cjw.chatting.dto.eventrecord.EventRecord;
import com.cjw.chatting.dto.eventrecord.payload.EventPayloadSaveMessage;
import com.cjw.chatting.service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import static com.cjw.chatting.util.constant.TopicConstant.TOPIC_SAVE_MESSAGE;

@Component
@RequiredArgsConstructor
public class ConsumerEventGateway {
    private final MessageService messageService;
    private final ObjectMapper mapper;

    public void handleEvent(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        //객체로 변환 후 타입?을 확인해서 분기처리하기 위함인데 지금 당장 필요할까
        try {
            switch (record.topic()) {
                case TOPIC_SAVE_MESSAGE:
                    this.messageService.saveMessage(EventRecord.of(
                            mapper.readValue(record.value(), EventPayloadSaveMessage.class),
                            record,
                            acknowledgment
                    ));
                    break;

            }
        } catch (Exception e) {
            //재시도 고려
            throw new RuntimeException(e);
        }
        //에러가 없는 경우만 커밋
        //재시도를 고려해야함
        acknowledgment.acknowledge();
    }
}
