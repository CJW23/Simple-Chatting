package com.cjw.chatting.domain.channel;

import com.cjw.chatting.domain.common.BaseEntity;
import com.cjw.chatting.dto.ChannelDto;
import com.cjw.chatting.dto.ChannelDto.CreateChannelDto;
import com.cjw.chatting.dto.exception.BasicException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.ObjectUtils.*;

@Entity
@Getter
@Table(name = "CHANNEL")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Channel extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHANNEL_ID", nullable = false)
    private Long channelId;

    @Column(name = "CHANNEL_NAME", nullable = false)
    private String channelName;

    @Column(name = "CHANNEL_DESCRIPTION")
    private String channelDescription;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "channel")
    private List<UserChannel> userChannels = new ArrayList<>();

    public static Channel createCommon(CreateChannelDto dto) {
        if(isEmpty(dto.getCreateUserId()))
            throw BasicException.ofBadRequest("채널을 생성한 유저가 존재하지 않습니다.");
        if(isEmpty(dto.getChannelName()))
            throw BasicException.ofBadRequest("채널명이 존재하지 않습니다.");

        return Channel.builder()
                .channelName(dto.getChannelName())
                .channelDescription(dto.getChannelDescription())
                .build();
    }

    public void addUserChannel(UserChannel userChannel) {
        if(this.userChannels == null) {
            this.userChannels = new ArrayList<>();
        }
        this.userChannels.add(userChannel);
        userChannel.setChannel(this);
    }
}
