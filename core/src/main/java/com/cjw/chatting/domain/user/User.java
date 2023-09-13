package com.cjw.chatting.domain.user;

import com.cjw.chatting.domain.channel.UserChannel;
import com.cjw.chatting.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity
@Getter
@DynamicUpdate
@Table(name = "USER")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "USER_NICKNAME", nullable = false)
    private String userNickname;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "user")
    private List<UserChannel> userChannels;
}
