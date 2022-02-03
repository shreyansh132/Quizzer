package com.shreyansh.quizzer.controller;

import com.shreyansh.quizzer.Exception.DataNotFoundException;
import com.shreyansh.quizzer.dto.CourseChapter;
import com.shreyansh.quizzer.entities.Chapter;
import com.shreyansh.quizzer.model.ResponseHandler;
import com.shreyansh.quizzer.services.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chapters")
@CrossOrigin("*")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

//    @PostMapping(value = "/add")
//    public ResponseEntity<Chapter> addChapter(@RequestBody Chapter chapter) {
//        return new ResponseEntity<>(chapterService.addChapter(chapter), HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<?> addChapterWithCourse(@RequestBody CourseChapter courseChapter) {
        if (!chapterService.courseExistsByIdOrName(courseChapter.getCourse()))
            throw new DataNotFoundException("Course with Name " + courseChapter.getCourse() + " does not exists");
        return ResponseHandler.generateResponse("Courses added to Database", HttpStatus.CREATED, chapterService.addChapterWithCourse(courseChapter));
    }

    @PostMapping(value = "/add")
    public ResponseEntity<List<Chapter>> addChapterList(@RequestBody List<Chapter> chapters) {
        return new ResponseEntity<>(chapterService.addChapterList(chapters), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chapter> getChapterById(@PathVariable String id) {
        return new ResponseEntity<>(chapterService.findChapterById(id), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<List<Chapter>> getChapters() {
        return new ResponseEntity<>(chapterService.findAllChapters(), HttpStatus.OK);
    }
}
