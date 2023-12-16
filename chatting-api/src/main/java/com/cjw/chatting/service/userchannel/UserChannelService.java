package com.cjw.chatting.service.userchannel;

import com.cjw.chatting.domain.channel.Channel;
import com.cjw.chatting.domain.channel.UserChannel;
import com.cjw.chatting.domain.channel.id.UserChannelId;
import com.cjw.chatting.domain.user.User;
import com.cjw.chatting.dto.exception.BasicException;
import com.cjw.chatting.repository.userchannel.UserChannelRepository;
import com.cjw.chatting.service.channel.ChannelService;
import com.cjw.chatting.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class UserChannelService {
    private final UserChannelRepository userChannelRepository;
    private final ChannelService channelService;
    private final UserService userService;

    /**
     * 채널에 유저 추가
     */
    public String addUser(Long channelId, Long userId) {
        Channel channel = this.channelService.findChannelByIdWithException(channelId);
        User user = this.userService.findUserByIdWithException(userId);

        //채널에 이미 가입돼있는지 확인
        channel.isRegisterUserWithException(userId);

        //해당 채널에 유저 추가
        channel.addUserChannel(UserChannel.create(channel, user, null));

        return "success";
    }

    /**
     * UserChannel 조회
     */
    public UserChannel findUserChannel(Long userId, Long channelId) {
        if (isEmpty(userId) || isEmpty(channelId)) return null;

        return userChannelRepository.findById(UserChannelId.of(userId, channelId))
                .orElse(null);
    }

    /**
     * UserChannel 조회
     */
    public UserChannel findUserChannelWithException(Long userId, Long channelId) {
        if (isEmpty(userId) || isEmpty(channelId))
            throw BasicException.ofBadRequest(String.format("userId: %d, channelId: %d is null", userId, channelId));

        return userChannelRepository.findById(UserChannelId.of(userId, channelId))
                .orElseThrow(() -> BasicException.ofBadRequest(String.format("userId: %d, channelId: %d 존재하지 않는 UserChannel", userId, channelId)));
    }
}
