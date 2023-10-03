package com.cjw.chatting.givenpackage;

import com.cjw.chatting.domain.user.User;
import com.cjw.chatting.dto.UserDto;

public class GivenPackage {
    public static User createTestUser() {
        return User.builder()
                .userNickname("test")
                .build();
    }

    public static UserDto.CreateUserDto createUserDto() {
        return new UserDto.CreateUserDto("testNickName");
    }

}
