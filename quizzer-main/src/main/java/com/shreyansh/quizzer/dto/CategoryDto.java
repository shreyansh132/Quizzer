package com.shreyansh.quizzer.dto;

import lombok.*;

import java.io.Serializable;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor @Builder
public class CategoryDto implements Serializable {
    private String categoryName;
    private String description;
    private String imageUrl;
}
