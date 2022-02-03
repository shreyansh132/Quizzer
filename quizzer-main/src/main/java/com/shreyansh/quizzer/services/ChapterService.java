package com.shreyansh.quizzer.services;

import com.shreyansh.quizzer.dto.CourseChapter;
import com.shreyansh.quizzer.entities.Category;
import com.shreyansh.quizzer.entities.Chapter;
import com.shreyansh.quizzer.entities.Course;

import java.util.List;

public interface ChapterService {
    List<Chapter> findAllChapters();
    Chapter findChapterByName(String chapterName);
    Chapter findChapterById(String chapterId);
    Chapter findChapterByChapterIdOrChapterName(String chapter);
    Course findCourseByCourseIdOrCourseName(String course);
    List<Chapter> addChapterList(List<Chapter> chapters);
    Chapter addChapter(Chapter chapter);

    Chapter updateChapterById(String chapter);
    Chapter updateChapter(Chapter chapter);

    boolean chapterExistsById(String id);
    boolean chapterExistsByName(String name);
    boolean chapterExistsByIdOrName(String chapter);
    boolean courseExistsByIdOrName(String course);
    List<Chapter> addChapterWithCourse(CourseChapter chapter);

    void deleteChapter(Chapter chapter);
    void deleteChapterById(Chapter chapter);
    void deleteAllChapters();
}
