package com.shreyansh.quizzer.repository;

import com.shreyansh.quizzer.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Question, String> {

}
