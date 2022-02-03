package com.shreyansh.quizzer.auth.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
@EntityListeners(value = {AuditingEntityListener.class})
public abstract class AuditorBase {

    @ManyToOne
    @JoinColumn(name = "createdById")
    @CreatedBy
    private User createdBy;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDateTime;

    @ManyToOne
    @JoinColumn(name = "lastModifiedById")
    @LastModifiedBy
    private User lastModifiedBy;

    @LastModifiedDate
    private LocalDateTime lastModifiedDateTime;
}
