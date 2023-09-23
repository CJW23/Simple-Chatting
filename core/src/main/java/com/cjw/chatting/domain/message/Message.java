package com.cjw.chatting.domain.message;

import com.cjw.chatting.domain.channel.UserChannel;
import com.cjw.chatting.domain.common.BaseEntity;
import com.cjw.chatting.dto.eventrecord.payload.EventPayloadSaveMessage;
import com.cjw.chatting.dto.exception.BasicException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.util.ObjectUtils.isEmpty;

@Entity
@Getter
@Table(name = "MESSAGE")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MESSAGE_ID", nullable = false)
    private Long messageId;

    @Column(name = "MESSAGE_TEXT")
    private String messageText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_CHANNEL_ID")
    private UserChannel userChannel;

    public void setUserChannel(UserChannel userChannel) {
        this.userChannel = userChannel;
        this.userChannel.addMessage(this);
    }

    public static Message createCommon(EventPayloadSaveMessage payload) {
        validate(payload);
        return Message.builder()
                .messageText(payload.getMessage())
                .build();
    }

    private static void validate(EventPayloadSaveMessage payload) {
        if (isEmpty(payload.getMessage())) {
            throw BasicException.ofBadRequest("메세지 내용이 없습니다.");
        }
    }
}
