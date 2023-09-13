package com.cjw.chatting.domain.message;

import com.cjw.chatting.domain.channel.UserChannel;
import com.cjw.chatting.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "MESSAGE")
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
}
