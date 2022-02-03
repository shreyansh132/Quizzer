package com.shreyansh.quizzer.auth.service;

import com.shreyansh.quizzer.auth.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void createAccount(){
        UserDto userDto = UserDto.builder()
                .dateOfBirth(LocalDateTime.of(1997,05,06,20,00))
                .firstName("Ansh")
                .lastName("Jain")
                .middleName("Kumar")
                .password("1234567")
                .email("anshj132@gmail.com")
                .build();
        userService.createAccount(userDto);
    }
}