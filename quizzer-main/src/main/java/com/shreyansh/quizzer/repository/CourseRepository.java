package com.shreyansh.quizzer.repository;

import com.shreyansh.quizzer.entities.Category;
import com.shreyansh.quizzer.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,String> {
    Course findCourseByCourseName(String name);
    Course findCourseByCourseIdOrCourseName(String id,String name);
    boolean existsByCourseIdOrCourseName(String id,String name);

}
