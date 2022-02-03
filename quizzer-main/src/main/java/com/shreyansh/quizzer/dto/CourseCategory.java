package com.shreyansh.quizzer.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class CourseCategory implements Serializable {
    String category;
    List<CourseDto> courses;
}
