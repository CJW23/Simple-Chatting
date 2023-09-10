package com.cjw.chatting.domain.channel;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
@Table(name = "CHANNEL")
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHANNEL_ID", nullable = false)
    private Long channelId;

    @Column(name = "CHANNEL_NAME", nullable = false)
    private String channelName;

    @Column(name = "CHANNEL_DESCRIPTION")
    private String channelDescription;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "channel")
    private List<UserChannel> userChannels;
}
