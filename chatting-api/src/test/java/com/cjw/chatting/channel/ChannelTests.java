package com.cjw.chatting.channel;

import com.cjw.chatting.CommonInit;
import com.cjw.chatting.domain.channel.Channel;
import com.cjw.chatting.domain.user.User;
import com.cjw.chatting.givenpackage.GivenPackage;
import com.cjw.chatting.repository.UserRepository;
import com.cjw.chatting.service.ChannelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static com.cjw.chatting.dto.ChannelDto.CreateChannelDto;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
public class ChannelTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChannelService channelService;
    private Long userId;

    @BeforeEach
    void beforeEach() {
        CommonInit.init();
        User user = userRepository.save(GivenPackage.createTestUser());
        userId = user.getUserId();
    }

    @Test
    void 채널_생성() {
        //given
        CreateChannelDto channelDto = new CreateChannelDto(userId, "test-channel", "this is test channel");

        //when
        Long channelId = channelService.createChannel(channelDto);

        //then
        Channel channel = channelService.findChannelById(channelId);
        assertNotNull(channel);
        assertEquals(channelDto.getChannelName(), channel.getChannelName());
        assertEquals(channelDto.getChannelDescription(), channel.getChannelDescription());
        assertTrue(channel.isRegisterUser(channelDto.getCreateUserId()));
    }
}
