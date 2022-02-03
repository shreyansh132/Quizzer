package com.shreyansh.quizzer.services;

import com.shreyansh.quizzer.entities.Question;

import java.util.List;

public interface QuizService {

    List<Question> getAllQuestions();
    List<Question> addQuestions(List<Question> quizQuestions);

}
