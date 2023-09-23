package com.cjw.chatting.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class BasicException extends RuntimeException {
    private HttpStatus status;

    private String errorMessage;

    public static BasicException ofBadRequest(String errorMessage) {
        return new BasicException(HttpStatus.BAD_REQUEST, errorMessage);
    }
}
