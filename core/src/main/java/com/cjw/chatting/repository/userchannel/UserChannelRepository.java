package com.cjw.chatting.repository.userchannel;

import com.cjw.chatting.domain.channel.UserChannel;
import com.cjw.chatting.domain.channel.id.UserChannelId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserChannelRepository extends JpaRepository<UserChannel, UserChannelId> {
}
