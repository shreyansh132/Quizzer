package com.shreyansh.quizzer.auth.repository;

import com.shreyansh.quizzer.auth.entity.OneTimePassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OtpRepository extends JpaRepository<OneTimePassword,String> {
       OneTimePassword findByUserId(String userId);
}
