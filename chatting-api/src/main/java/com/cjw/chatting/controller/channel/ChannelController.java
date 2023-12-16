package com.cjw.chatting.controller.channel;

import com.cjw.chatting.dto.ChannelDto.CreateChannelDto;
import com.cjw.chatting.service.channel.ChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/channel")
public class ChannelController {
    private final ChannelService channelService;

    @PostMapping(value = "", name = "채널 생성")
    public ResponseEntity<String> createChannel(@RequestBody CreateChannelDto dto) {
        this.channelService.createChannel(dto);
        return ResponseEntity.ok("OK");    //todo 추후 Response 객체로
    }
}
