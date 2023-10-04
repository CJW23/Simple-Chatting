package com.cjw.chatting.repository.message;

import com.cjw.chatting.domain.channel.QChannel;
import com.cjw.chatting.domain.message.Message;
import com.cjw.chatting.domain.user.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MessageRepositoryQueryImpl implements MessageRepositoryQuery{
    private final JPAQueryFactory query;

    @Override
    public List<Message> findMessageByUserAndChannel(Long userId, Long channelId) {
        QUser user = QUser.user;
        QChannel channel = QChannel.channel;
        return null;
    }
}
