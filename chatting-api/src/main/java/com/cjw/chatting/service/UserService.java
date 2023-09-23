package com.cjw.chatting.service;

import com.cjw.chatting.domain.user.User;
import com.cjw.chatting.dto.UserDto.CreateUserDto;
import com.cjw.chatting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void createUser(CreateUserDto createUserDto) {
        User user = User.create(createUserDto);
        //닉네임 중복체크?
        this.userRepository.save(user);
    }

    public User findUserById(Long userId) {
        if (isEmpty(userId)) return null;

        return this.userRepository.findById(userId).orElse(null);
    }
}
