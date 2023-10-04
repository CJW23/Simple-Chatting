package com.cjw.chatting.repository.channel;

import com.cjw.chatting.domain.channel.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
}
