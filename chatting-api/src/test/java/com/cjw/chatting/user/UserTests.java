package com.cjw.chatting.user;

import com.cjw.chatting.CommonInit;
import com.cjw.chatting.domain.user.User;
import com.cjw.chatting.givenpackage.GivenPackage;
import com.cjw.chatting.service.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static com.cjw.chatting.dto.UserDto.CreateUserDto;

@SpringBootTest
@Transactional
@Rollback
public class UserTests {
    @Autowired
    private UserService userService;

    @BeforeEach
    void beforeEach() {
        CommonInit.init();
    }

    @Test
    void 유저_생성() {
        //given
        CreateUserDto testNickName = GivenPackage.createUserDto();
        //when
        Long userId = this.userService.createUser(testNickName);
        User user = this.userService.findUserById(userId);
        //then
        Assertions.assertNotNull(user);
        Assertions.assertEquals(testNickName.getNickName(), user.getUserNickname());
    }
}
