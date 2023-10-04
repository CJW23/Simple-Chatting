package com.cjw.chatting.repository.message;

import com.cjw.chatting.domain.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long>, MessageRepositoryQuery {
}
