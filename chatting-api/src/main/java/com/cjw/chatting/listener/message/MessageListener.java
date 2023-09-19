package com.cjw.chatting.listener.message;

import com.cjw.chatting.listener.gateway.ConsumerEventGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageListener {
    private final ConsumerEventGateway gateway;

    @KafkaListener(topics = "${spring.kafka.topic.topic-save-message}", groupId = "${spring.kafka.group-id}")
    public void saveMessageConsume(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        gateway.handleEvent(record, acknowledgment);
    }
}
