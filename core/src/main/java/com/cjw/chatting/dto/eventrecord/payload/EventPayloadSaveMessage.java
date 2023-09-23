package com.cjw.chatting.dto.eventrecord.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventPayloadSaveMessage extends EventPayload {
    private long channelId;
    private long userId;
    private String message;
    private LocalDateTime sendDate;

}
