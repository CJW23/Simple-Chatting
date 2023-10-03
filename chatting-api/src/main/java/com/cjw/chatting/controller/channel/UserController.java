package com.cjw.chatting.controller.channel;

import com.cjw.chatting.dto.UserDto.CreateUserDto;
import com.cjw.chatting.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/user")
public class UserController {
    private final UserService userService;

    @PostMapping(value = "", name = "유저 생성")
    public ResponseEntity<Long> createUser(@Valid @RequestBody CreateUserDto dto) {
        return ResponseEntity.ok(this.userService.createUser(dto));
    }
}
