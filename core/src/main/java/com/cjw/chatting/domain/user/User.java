package com.cjw.chatting.domain.user;

import com.cjw.chatting.domain.channel.UserChannel;
import com.cjw.chatting.domain.common.BaseEntity;
import com.cjw.chatting.dto.UserDto.CreateUserDto;
import com.cjw.chatting.dto.exception.BasicException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Entity
@Getter
@DynamicUpdate
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "USER_NICKNAME", nullable = false)
    private String userNickname;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "user")
    private List<UserChannel> userChannels = new ArrayList<>();

    public static User create(CreateUserDto dto) {
        if(isEmpty(dto.getNickName())) {
            throw BasicException.ofBadRequest("닉네임이 비어있습니다.");
        }
        return User.builder()
                .userNickname(dto.getNickName())
                .build();
    }

    public void addUserChannel(UserChannel userChannel) {
        if(userChannels == null) {
            this.userChannels = new ArrayList<>();
        }
        this.userChannels.add(userChannel);
        userChannel.setUser(this);
    }
}
