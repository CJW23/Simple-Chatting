package com.cjw.chatting.dto.eventrecord.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
public abstract class EventPayload {
    private String topic;
}
