package com.shreyansh.quizzer.vo;

import com.shreyansh.quizzer.entities.Category;
import lombok.Data;

@Data
public class RestTemplateVO {

    private Category quizCategory;
    private String message;

}
