package com.shreyansh.quizzer.dto;

import lombok.*;

import java.util.List;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor @Builder
public class ChapterQuestion {
    String chapter;
    List<QuestionsDto> questionsDtoList;
}
