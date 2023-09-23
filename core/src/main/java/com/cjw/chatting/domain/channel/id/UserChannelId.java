package com.cjw.chatting.domain.channel.id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserChannelId implements Serializable {
    private Long userId;
    private Long channelId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserChannelId that = (UserChannelId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(channelId, that.channelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, channelId);
    }

    public static UserChannelId of(Long userId, Long channelId) {
        return new UserChannelId(userId, channelId);
    }
}