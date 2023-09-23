package com.cjw.chatting.domain.channel;

import com.cjw.chatting.domain.channel.id.UserChannelId;
import com.cjw.chatting.domain.common.BaseEntity;
import com.cjw.chatting.domain.message.Message;
import com.cjw.chatting.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@IdClass(UserChannelId.class)
@Table(name = "USER_CHANNEL")
public class UserChannel extends BaseEntity {
    @Id
    @Column(name = "CHANNEL_ID")
    private Long channelId;

    @Id
    @Column(name = "USER_ID")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHANNEL_ID", insertable = false, updatable = false)
    private Channel channel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
    private User user;

    @OneToMany(mappedBy = "userChannel", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages = new ArrayList<>();


    public void addMessage(Message message) {
        if (this.messages == null) {
            this.messages = new ArrayList<>();
        }
        this.messages.add(message);
    }
}
