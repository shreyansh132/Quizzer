package com.shreyansh.quizzer.services;

import com.shreyansh.quizzer.Exception.DataNotFoundException;
import com.shreyansh.quizzer.dto.CourseCategory;
import com.shreyansh.quizzer.entities.Category;
import com.shreyansh.quizzer.entities.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();
    List<Course> addCourseList(List<Course> courses);
    Course addCourse(Course course);
    Course getCourseByName(String courseName);
    Course getCourseById(String courseId);
    Course getCourseByIdOrName(String course);
    void deleteCourse(Course course);
    Course updateCourse(Course course);
    boolean categoryExists(String category);
    List<Course> addCourseWithCategory(CourseCategory courseCategory);
    Category findCategoryByNameOrId(String category);
}
