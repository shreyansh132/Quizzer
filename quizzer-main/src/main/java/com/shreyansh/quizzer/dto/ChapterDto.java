package com.shreyansh.quizzer.dto;


import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class ChapterDto {
    private String chapterName;
    private String description;
    private String imageUrl;
}
