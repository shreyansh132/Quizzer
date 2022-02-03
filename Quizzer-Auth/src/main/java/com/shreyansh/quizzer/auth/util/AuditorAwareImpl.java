package com.shreyansh.quizzer.auth.util;

import com.shreyansh.quizzer.auth.entity.User;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<User> {

   public static User user;

    @Override
    public Optional<User> getCurrentAuditor()
    {
        return Optional.of(user);
    }
}
