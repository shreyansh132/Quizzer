package com.shreyansh.quizzer.auth.vo;

import com.shreyansh.quizzer.auth.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTemplateVO {
    private User user;
}
