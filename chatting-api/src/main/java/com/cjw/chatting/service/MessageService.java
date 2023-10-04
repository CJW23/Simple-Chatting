package com.cjw.chatting.service;

import com.cjw.chatting.domain.channel.UserChannel;
import com.cjw.chatting.domain.message.Message;
import com.cjw.chatting.dto.eventrecord.EventRecord;
import com.cjw.chatting.dto.eventrecord.payload.EventPayloadSaveMessage;
import com.cjw.chatting.dto.exception.BasicException;
import com.cjw.chatting.repository.message.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.util.ObjectUtils.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChannelService channelService;

    @Transactional
    public void saveMessage(EventRecord eventRecord) {
        EventPayloadSaveMessage payload = (EventPayloadSaveMessage) eventRecord.getEventPayload();

        log.info("==============saveMessagePayload================");
        log.info(payload.toString());

        //기본 메세지 엔티티 생성
        Message message = Message.createCommon(payload);

        //해당 메세지 전송 유저 채널
        UserChannel userChannel = this.channelService.findUserChannel(payload.getUserId(), payload.getChannelId());
        if (isEmpty(userChannel)) throw BasicException.ofBadRequest("존재하지 않는 유저 채널입니다.");

        userChannel.addMessage(message);

        this.messageRepository.save(message);
    }
}
