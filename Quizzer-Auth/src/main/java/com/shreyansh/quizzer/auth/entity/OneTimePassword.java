package com.shreyansh.quizzer.auth.entity;

import com.shreyansh.quizzer.auth.util.UniqueIdGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OneTimePassword {

    @Id
    @GeneratedValue(generator = UniqueIdGenerator.generatorName, strategy = GenerationType.AUTO)
    @GenericGenerator(name = UniqueIdGenerator.generatorName, strategy = "com.shreyansh.quizzer.auth.util.UniqueIdGenerator")
    @Column(name = "otpId", length = 42, unique = true, updatable = false)
    String otpId;
    @Column(name = "userId", length = 42, unique = true, updatable = false)
    String userId;
    @Column(name = "otp", length = 6)
    String otp;
    LocalDateTime expiresOn;
}
