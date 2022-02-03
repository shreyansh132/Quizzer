package com.shreyansh.quizzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableEurekaClient
//@OpenAPIDefinition(info = @Info(title = "Quizzer App", version = "2.0", description = "Quizzer Application"))
public class QuizzerApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuizzerApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
