package com.shreyansh.quizzer.dto;


import lombok.*;

import java.io.Serializable;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString @Builder
public class CourseDto implements Serializable {
    private String courseName;
    private String description;
}
