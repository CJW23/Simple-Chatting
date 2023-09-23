package com.cjw.chatting.dto.eventrecord;

import com.cjw.chatting.dto.eventrecord.payload.EventPayload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;

/**
 * 카프카 컨슈머 공통 처리 Event Data
 */
@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class EventRecord {
    private ConsumerRecord<String, String> record;
    private EventPayload eventPayload;
    private Acknowledgment acknowledgment;

    public static EventRecord of(EventPayload eventPayload, ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        return EventRecord.builder()
                .eventPayload(eventPayload)
                .record(record)
                .acknowledgment(acknowledgment)
                .build();
    }
}
