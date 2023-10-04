package com.cjw.chatting.service;

import com.cjw.chatting.domain.channel.Channel;
import com.cjw.chatting.domain.channel.UserChannel;
import com.cjw.chatting.domain.channel.id.UserChannelId;
import com.cjw.chatting.domain.user.User;
import com.cjw.chatting.dto.ChannelDto.CreateChannelDto;
import com.cjw.chatting.dto.exception.BasicException;
import com.cjw.chatting.repository.channel.ChannelRepository;
import com.cjw.chatting.repository.userchannel.UserChannelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChannelService {
    private final ChannelRepository channelRepository;
    private final UserService userService;
    private final UserChannelRepository userChannelRepository;

    @Transactional
    public Long createChannel(CreateChannelDto createChannelDto) {
        User user = this.userService.findUserById(createChannelDto.getCreateUserId());
        if (isEmpty(user)) throw BasicException.ofBadRequest("유저가 존재하지 않습니다.");
        //채널 생성
        Channel channel = Channel.createCommon(createChannelDto);
        //유저 채널 생성
        UserChannel userChannel = UserChannel.create(channel, user, null);

        //관계
        user.addUserChannel(userChannel);
        channel.addUserChannel(userChannel);

        channelRepository.save(channel);

        return channel.getChannelId();
    }

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
