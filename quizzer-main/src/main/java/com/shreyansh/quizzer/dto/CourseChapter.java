package com.shreyansh.quizzer.dto;

import lombok.*;

import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class CourseChapter {
    private String course;
    private List<ChapterDto> chapters;
}
