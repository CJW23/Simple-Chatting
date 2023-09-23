package com.cjw.chatting.service;

import com.cjw.chatting.domain.channel.Channel;
import com.cjw.chatting.domain.channel.UserChannel;
import com.cjw.chatting.domain.channel.id.UserChannelId;
import com.cjw.chatting.repository.ChannelRepository;
import com.cjw.chatting.repository.UserChannelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChannelService {
    private final ChannelRepository channelRepository;
    private final UserChannelRepository userChannelRepository;

    public Channel findChannelById(Long channelId) {
        if (isEmpty(channelId)) return null;

        return this.channelRepository.findById(channelId).orElse(null);
    }

    public UserChannel findUserChannel(Long userId, Long channelId) {
        if (isEmpty(userId) || isEmpty(channelId)) return null;

        return userChannelRepository.findById(UserChannelId.of(userId, channelId))
                .orElse(null);
    }
}
