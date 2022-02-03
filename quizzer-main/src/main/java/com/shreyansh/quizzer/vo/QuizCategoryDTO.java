package com.shreyansh.quizzer.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizCategoryDTO {
    private String id;
    private String categoryText;
    private String description;
    private MultipartFile imageFile;
}
