package com.shreyansh.quizzer.serviceImpl;

import com.shreyansh.quizzer.dto.ChapterQuestion;
import com.shreyansh.quizzer.entities.Chapter;
import com.shreyansh.quizzer.entities.Question;
import com.shreyansh.quizzer.repository.ChapterRepository;
import com.shreyansh.quizzer.repository.QuestionsRepository;
import com.shreyansh.quizzer.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionsRepository questionsRepository;

    @Autowired
    private ChapterRepository chapterRepository;

    @Override
    public List<Question> addQuestionsList(ChapterQuestion chapterQuestions) {
        Chapter chapter =  chapterRepository.findChapterByChapterIdOrChapterName(chapterQuestions.getChapter(),chapterQuestions.getChapter());
        List<Question> questions = new ArrayList<>();
        chapterQuestions.getQuestionsDtoList().forEach(
                questionsDto -> {
                    Question question = Question.builder()
                            .chapter(chapter)
                            .question(questionsDto.getQuestion())
                            .answer(questionsDto.getAnswer())
                            .answerExplanation(questionsDto.getAnswerExplanation())
                            .optionA(questionsDto.getOptionA())
                            .optionB(questionsDto.getOptionB())
                            .optionC(questionsDto.getOptionC())
                            .optionD(questionsDto.getOptionD())
                            .optionE(questionsDto.getOptionE())
                            .optionF(questionsDto.getOptionF())
                            .build();
                    questions.add(question);
        });
       return questionsRepository.saveAll(questions);
    }

    @Override
    public boolean chapterExistsByIdOrName(String chapter) {
        return chapterRepository.existsByChapterIdOrChapterName(chapter,chapter);
    }
}
