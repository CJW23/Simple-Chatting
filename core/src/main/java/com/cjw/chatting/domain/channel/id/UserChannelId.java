package com.cjw.chatting.domain.channel.id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class UserChannelId implements Serializable {
    private Long channel;
    private Long user;

    public static UserChannelId of(Long userId, Long channelId) {
        return new UserChannelId(userId, channelId);
    }
}