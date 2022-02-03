package com.shreyansh.quizzer.services;

import com.shreyansh.quizzer.dto.ChapterQuestion;
import com.shreyansh.quizzer.entities.Question;

import java.util.List;

public interface QuestionService {
    List<Question> addQuestionsList(ChapterQuestion chapterQuestion);
    boolean chapterExistsByIdOrName(String chapter);
}
