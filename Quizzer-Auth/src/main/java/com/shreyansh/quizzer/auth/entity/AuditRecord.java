package com.shreyansh.quizzer.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditRecord {
    private String createdBy;
    @Column(length = 42)
    private String createdById;
    private String lastModifiedBy;
    @Column(length = 42)
    private String lastModifiedById;
    private LocalDateTime createdDateTime;
    private LocalDateTime lastModifiedDateTime;
}
