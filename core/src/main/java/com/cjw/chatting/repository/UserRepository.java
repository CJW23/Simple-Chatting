package com.cjw.chatting.repository;

import com.cjw.chatting.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
