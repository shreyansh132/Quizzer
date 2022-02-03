package com.shreyansh.quizzer.auth.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shreyansh.quizzer.auth.util.UniqueIdGenerator;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "roles")
public class UserRoles {

    @Id
    @GeneratedValue(generator = UniqueIdGenerator.generatorName, strategy = GenerationType.AUTO)
    @GenericGenerator(name = UniqueIdGenerator.generatorName, strategy = "com.shreyansh.quizzer.auth.util.UniqueIdGenerator")
    @Column(name = "roleId", length = 42, unique = true)
    private String roleId;

    @Column(length = 100, unique = true)
    private String roleName;

    public UserRoles(String roleName) {
        this.roleName = roleName;
    }

    //Used Transient Annotation as it was causing stackoverflow error because of recursion
    @JsonIgnoreProperties("userRoles")
    @ManyToMany(
            fetch = FetchType.EAGER,
            mappedBy = "userRoles")
    List<User> users;

    @Embedded
    private AuditRecord auditRecord;
}
