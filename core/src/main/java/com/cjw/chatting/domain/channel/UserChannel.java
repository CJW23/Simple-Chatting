package com.cjw.chatting.domain.channel;

import com.cjw.chatting.domain.channel.id.UserChannelId;
import com.cjw.chatting.domain.common.BaseEntity;
import com.cjw.chatting.domain.message.Message;
import com.cjw.chatting.domain.user.User;
import com.cjw.chatting.dto.exception.BasicException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.ObjectUtils.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UserChannelId.class)
@Table(name = "USER_CHANNEL")
public class UserChannel extends BaseEntity {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHANNEL_ID")
    private Channel channel;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "userChannel", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages = new ArrayList<>();

    public static UserChannel create(Channel channel, User user, List<Message> messages) {
        if(isEmpty(channel) || isEmpty(user)) {
            throw BasicException.ofBadRequest("채널 또는 유저가 존재하지 않습니다.");
        }
        return UserChannel.builder()
                .user(user)
                .channel(channel)
                .messages(messages)
                .build();
    }

    public void addMessage(Message message) {
        if (this.messages == null) {
            this.messages = new ArrayList<>();
        }
        this.messages.add(message);
        message.setUserChannel(this);
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
