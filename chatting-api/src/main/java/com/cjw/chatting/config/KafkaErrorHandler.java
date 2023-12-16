package com.cjw.chatting.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;

@RequiredArgsConstructor
@Configuration
@Slf4j
public class KafkaErrorHandler implements CommonErrorHandler {
    /*@Override
    public boolean handleOne(Exception thrownException, ConsumerRecord<?, ?> record, Consumer<?, ?> consumer, MessageListenerContainer container) {
        log.error("===========" + record.offset() + "=========");

        return true;
        // return CommonErrorHandler.super.handleOne(thrownException, record, consumer, container);
    }*/
}
