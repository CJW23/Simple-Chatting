package com.cjw.chatting.givenpackage;

import com.cjw.chatting.domain.user.User;

public class GivenPackage {
    public static User createTestUser() {
        return User.builder()
                .userNickname("test")
                .build();
    }

}
