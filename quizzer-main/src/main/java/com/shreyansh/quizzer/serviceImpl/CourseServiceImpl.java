package com.shreyansh.quizzer.serviceImpl;

import com.shreyansh.quizzer.Exception.DataNotFoundException;
import com.shreyansh.quizzer.dto.CourseCategory;
import com.shreyansh.quizzer.entities.Category;
import com.shreyansh.quizzer.entities.Course;
import com.shreyansh.quizzer.repository.CategoryRepository;
import com.shreyansh.quizzer.repository.CourseRepository;
import com.shreyansh.quizzer.services.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> addCourseList(List<Course> courses) {
        return courseRepository.saveAll(courses);
    }

    @Override
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course getCourseByName(String courseName) {
        return courseRepository.findCourseByCourseName(courseName);
    }

    @Override
    public Course getCourseById(String courseId) {
        return courseRepository.findById(courseId).get();
    }

    public Course getCourseByIdOrName(String course){
        return courseRepository.findCourseByCourseIdOrCourseName(course, course);
    }

    @Override
    public void deleteCourse(Course course) {
        courseRepository.delete(course);
    }

    @Override
    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> addCourseWithCategory(CourseCategory courseCategory) {
        Category category = findCategoryByNameOrId(courseCategory.getCategory());
        List<Course> courses = new ArrayList<>();
        courseCategory.getCourses().forEach(courseDto -> {
            Course course = Course.builder()
                    .courseName(courseDto.getCourseName())
                    .description(courseDto.getDescription())
                    .category(category)
                    .build();
            courses.add(course);
        });
        return courseRepository.saveAll(courses);
    }

    @Override
    public boolean categoryExists(String category) {
        return categoryRepository.existsByCategoryIdOrCategoryName(category, category);
    }

   public Category findCategoryByNameOrId(String category){
       return categoryRepository.findByCategoryIdOrCategoryName(category,category);
    }


}
