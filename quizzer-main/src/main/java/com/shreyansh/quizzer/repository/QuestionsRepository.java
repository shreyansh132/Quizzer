package com.shreyansh.quizzer.repository;

import com.shreyansh.quizzer.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsRepository extends JpaRepository<Question,String> { }
