package com.shreyansh.quizzer.auth.model;

import lombok.*;

import java.util.Date;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class JwtToken {
    private String token;
    private Date issuedAt;
    private Date validTill;
    private Boolean isTokenExpired;
}
