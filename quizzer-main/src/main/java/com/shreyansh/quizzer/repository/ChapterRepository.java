package com.shreyansh.quizzer.repository;

import com.shreyansh.quizzer.entities.Category;
import com.shreyansh.quizzer.entities.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, String> {
    Chapter findChapterByChapterName(String chapter);
    Chapter findChapterByChapterIdOrChapterName(String id, String name);
    boolean existsByChapterName(String name);
    boolean existsByChapterIdOrChapterName(String id, String name);
}
