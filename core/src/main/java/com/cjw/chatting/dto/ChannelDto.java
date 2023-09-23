package com.cjw.chatting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ChannelDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateChannelDto {
        private Long createUserId;
        private String channelName;
        private String channelDescription;

    }
}
