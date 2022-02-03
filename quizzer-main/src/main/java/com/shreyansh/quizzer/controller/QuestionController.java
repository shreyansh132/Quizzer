package com.shreyansh.quizzer.controller;

import java.util.List;

import com.shreyansh.quizzer.Exception.DataNotFoundException;
import com.shreyansh.quizzer.dto.ChapterQuestion;
import com.shreyansh.quizzer.dto.QuestionsDto;
import com.shreyansh.quizzer.entities.Question;
import com.shreyansh.quizzer.services.QuestionService;
import com.shreyansh.quizzer.services.QuizService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
@Slf4j
public class QuestionController {

    @Autowired
    private QuestionService questionService;

//    @GetMapping("/message")
//    public ResponseEntity<RestTemplateVO> getMessage(){
//        return new ResponseEntity<>(quizService.getUserWithAllQuizzes(),HttpStatus.OK);
//    }

    @PostMapping("/add")
    public ResponseEntity<List<Question>> addQuestions(@RequestBody ChapterQuestion chapterQuestion){
        if(!questionService.chapterExistsByIdOrName(chapterQuestion.getChapter())) throw new DataNotFoundException("Chapter with Name "+ chapterQuestion.getChapter()+ " doesn't exists");
        return new ResponseEntity<>(questionService.addQuestionsList(chapterQuestion), HttpStatus.CREATED);
    }

//    @GetMapping(value = "/all")
//    ResponseEntity<List<Question>> getQuestions(){
//        return new ResponseEntity<>(quizService.getAllQuestions(), HttpStatus.OK);
//    }
}
