package com.cjw.chatting.dto.eventrecord.payload;

import com.cjw.chatting.domain.message.Message;
import com.cjw.chatting.dto.ChattingMessageDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

import static com.cjw.chatting.util.constant.TopicConstant.TOPIC_SAVE_MESSAGE;

@Getter
@SuperBuilder
@NoArgsConstructor
@ToString
public class EventPayloadSaveMessage extends EventPayload {
    private long channelId;
    private long userId;
    private ChattingMessageDto message;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime sendDate;

    public static EventPayloadSaveMessage of(long channelId, long userId, ChattingMessageDto message) {
        return EventPayloadSaveMessage.builder()
                .topic(TOPIC_SAVE_MESSAGE)
                .channelId(channelId)
                .userId(userId)
                .message(message)
                .sendDate(LocalDateTime.now())
                .build();
    }
}
