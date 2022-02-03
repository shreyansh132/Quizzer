package com.shreyansh.quizzer.serviceImpl;

import com.shreyansh.quizzer.dto.CourseChapter;
import com.shreyansh.quizzer.entities.Chapter;
import com.shreyansh.quizzer.entities.Course;
import com.shreyansh.quizzer.repository.ChapterRepository;
import com.shreyansh.quizzer.repository.CourseRepository;
import com.shreyansh.quizzer.services.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Chapter> findAllChapters() {
        return chapterRepository.findAll();
    }

    @Override
    public Chapter findChapterByName(String chapterName) {
        return chapterRepository.findChapterByChapterName(chapterName);
    }

    @Override
    public Chapter findChapterById(String chapterId) {
        return chapterRepository.findById(chapterId).get();
    }

    @Override
    public Chapter findChapterByChapterIdOrChapterName(String chapter) {
        return chapterRepository.findChapterByChapterIdOrChapterName(chapter, chapter);
    }

    @Override
    public List<Chapter> addChapterList(List<Chapter> chapters) {
        return chapterRepository.saveAll(chapters);
    }

    @Override
    public Chapter addChapter(Chapter chapter) {
        return chapterRepository.save(chapter);
    }

    @Override
    public Chapter updateChapterById(String chapter) {
        return null;
    }

    @Override
    public Chapter updateChapter(Chapter chapter) {
        return chapterRepository.save(chapter);
    }

    @Override
    public boolean chapterExistsById(String id) {
        return chapterRepository.existsById(id);
    }

    @Override
    public boolean chapterExistsByName(String name) {
        return chapterRepository.existsByChapterName(name);
    }

    @Override
    public boolean chapterExistsByIdOrName(String chapter) {
        return chapterRepository.existsByChapterIdOrChapterName(chapter, chapter);
    }

    @Override
    public List<Chapter> addChapterWithCourse(CourseChapter courseChapter) {
        Course course = findCourseByCourseIdOrCourseName(courseChapter.getCourse());
        List<Chapter> chapters = new ArrayList<>();
        courseChapter.getChapters().forEach(
                chapterDto -> {
                    Chapter chapter = Chapter.builder()
                            .imageUrl(chapterDto.getImageUrl())
                            .description(chapterDto.getDescription())
                            .chapterName(chapterDto.getChapterName())
                            .course(course)
                            .build();
                    chapters.add(chapter);
                });
        chapterRepository.saveAll(chapters);
        return chapters;
    }

    @Override
    public boolean courseExistsByIdOrName(String course) {
        return courseRepository.existsByCourseIdOrCourseName(course, course);
    }

    public Course findCourseByCourseIdOrCourseName(String course) {
        return courseRepository.findCourseByCourseIdOrCourseName(course, course);
    }

    @Override
    public void deleteChapter(Chapter chapter) {
        chapterRepository.delete(chapter);
    }

    @Override
    public void deleteChapterById(Chapter Chapter) {

    }

    @Override
    public void deleteAllChapters() {
        chapterRepository.deleteAll();
    }
}
