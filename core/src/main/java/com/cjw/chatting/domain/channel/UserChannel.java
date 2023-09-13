package com.cjw.chatting.domain.channel;

import com.cjw.chatting.domain.common.BaseEntity;
import com.cjw.chatting.domain.message.Message;
import com.cjw.chatting.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
@Table(name = "USER_CHANNEL")
public class UserChannel extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_CHANNEL_ID", nullable = false)
    private Long userChannelId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHANNEL_ID")
    private Channel channel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "userChannel",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages;
}
