package com.shreyansh.quizzer.auth;

import com.shreyansh.quizzer.auth.service.UserService;
import com.shreyansh.quizzer.auth.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class QuizzerUserAuthTests {

	@Autowired
	private UserService userService;

	@Test
	public void showUsers() {
		System.out.println(userService.getAllUsers());
	}

	@Test
	public void createUser() {
		UserDto userDto = UserDto.builder()
				.firstName("Shreyansh")
				.lastName("Jain")
				.email("shreyanshj132@gmail.com")
				.mobile("2345678")
				.dateOfBirth(LocalDateTime.of(1997,06,05,20,00))
				.password("23456723")
				.build();
		userService.createAccount(userDto);
	}
}
