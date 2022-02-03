package com.shreyansh.quizzer.auth.batch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBatchVo {
    private String userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String fullName;
    private String dateOfBirth;
    private String password;
    private String mobile;
    private String email;
}
