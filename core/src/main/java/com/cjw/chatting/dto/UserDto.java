package com.cjw.chatting.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateUserDto {
        @NotNull
        @Size(min = 2, max = 10, message = "닉네임은 2 ~ 10글자")
        private String nickName;
    }
}
