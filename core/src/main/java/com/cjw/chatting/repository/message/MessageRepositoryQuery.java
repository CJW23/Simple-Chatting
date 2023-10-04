package com.cjw.chatting.repository.message;

import com.cjw.chatting.domain.message.Message;

import java.util.List;

public interface MessageRepositoryQuery {
    List<Message> findMessageByUserAndChannel(Long userId, Long channelId);
}
