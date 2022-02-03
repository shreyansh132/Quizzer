package com.shreyansh.quizzer.controller;

import com.shreyansh.quizzer.Exception.DataNotFoundException;
import com.shreyansh.quizzer.model.ResponseHandler;
import com.shreyansh.quizzer.dto.CourseCategory;
import com.shreyansh.quizzer.entities.Course;
import com.shreyansh.quizzer.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@CrossOrigin("*")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    ResponseEntity<List<Course>> getCategories() {
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    }

    @GetMapping("nameOrId")
    public ResponseEntity<Course> getCourseByIdOrName(@RequestParam(name = "value") String nameOrId) {
        return new ResponseEntity<>(courseService.getCourseByIdOrName(nameOrId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable String id) {
        return new ResponseEntity<>(courseService.getCourseById(id), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody Course quizCourse) {
        return new ResponseEntity<>(courseService.addCourse(quizCourse), HttpStatus.CREATED);
    }

    @PostMapping(value = "/list")
    public ResponseEntity<List<Course>> addCourseList(@RequestBody List<Course> quizCategories) {
        return new ResponseEntity<>(courseService.addCourseList(quizCategories), HttpStatus.CREATED);
    }

    @PostMapping(value = "/v2/add")
    public ResponseEntity<?> addCourseWithCategory(@RequestBody CourseCategory courseCategory) {
        if(!courseService.categoryExists(courseCategory.getCategory())) throw new DataNotFoundException("Category with Name " + courseCategory.getCategory() + " does not exists");
        return ResponseHandler.generateResponse("Categories added to Database",HttpStatus.CREATED,courseService.addCourseWithCategory(courseCategory));
    }
}
