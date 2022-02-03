package com.shreyansh.quizzer.auth.model;

import com.shreyansh.quizzer.auth.vo.UserVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {
    private JwtToken jwtToken;
    private UserVo user;
}
