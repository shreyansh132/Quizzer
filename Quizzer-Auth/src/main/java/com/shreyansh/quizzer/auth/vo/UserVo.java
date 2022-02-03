package com.shreyansh.quizzer.auth.vo;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserVo implements Serializable {
    private String userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String fullName;
    private LocalDateTime dateOfBirth;
    private String email;
    private String mobile;
    private String imageUrl;
    private Collection<? extends GrantedAuthority> authorities;

}
