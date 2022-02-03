package com.shreyansh.quizzer.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

    private String fullName;
    private String password;
    private String mobile;
    private String email;

}
