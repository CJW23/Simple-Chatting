package com.cjw.chatting.controller.userchannel;

import com.cjw.chatting.service.userchannel.UserChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/channel-user")
@RequiredArgsConstructor
public class UserChannelController {
    private final UserChannelService userChannelService;

    @PostMapping(value = "/channel/{channelId}/user/{userId}")
    public String addUser(@PathVariable("channelId") Long channelId, @PathVariable("userId") Long userId) {
        return this.userChannelService.addUser(channelId, userId);
    }
}
