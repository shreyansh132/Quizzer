package com.shreyansh.quizzer.auth.util;

import com.shreyansh.quizzer.auth.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditorConfig {

    @Bean
    AuditorAware<User> auditorProvider(){
        return new AuditorAwareImpl();
    }
}
