package com.shreyansh.quizzer.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shreyansh.quizzer.auth.util.UniqueIdGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Slf4j
@Builder
@Data
@Table( name = "users",
        uniqueConstraints = {
        @UniqueConstraint(name = "user_email_unique", columnNames = "email")
})

public class User implements UserDetails {
    @Id
    @GeneratedValue(generator = UniqueIdGenerator.generatorName, strategy = GenerationType.AUTO)
    @GenericGenerator(name = UniqueIdGenerator.generatorName, strategy = "com.shreyansh.quizzer.auth.util.UniqueIdGenerator")
    @Column(name = "userId", length = 42, unique = true, updatable = false)
    private String userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String fullName;
    private LocalDateTime dateOfBirth;
    private String password;
    @Column(name = "email", unique = true)
    private String email;

    private String mobile;
    @Column(columnDefinition = "boolean default false")
    private Boolean isLocked;
    private String imageUrl;

    @Column(columnDefinition = "boolean default true")
    private Boolean active;

    @Embedded
    private AuditRecord auditRecord;

    @JsonIgnoreProperties("users")
    @JsonIgnore
    @ManyToMany(
            fetch = FetchType.EAGER
            ,cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles_mapping"
            ,joinColumns = {
                @JoinColumn(
                   name = "user_id",
                   referencedColumnName = "userId"
                )
            }
            ,inverseJoinColumns = {
                @JoinColumn(
                   name = "role_id",
                   referencedColumnName = "roleId")
            })
    private Set<UserRoles> userRoles;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private List<Address> addresses;

    @PrePersist
    public void onPrePersist() {
        if(middleName != null) fullName = firstName + " " +middleName + " " +lastName;
        else fullName = firstName + " " +lastName;
    }

    @PostPersist
    public void onPostPersist() {}

    @PreRemove
    public void onPreRemove() {
        log.info("Attempting to delete user: " + firstName);
    }

    @PostRemove
    public void onPostRemove() {
        log.info("Deleted user: " + firstName);
    }

    @PreUpdate
    public void onPreUpdate(){
        if(middleName != null) fullName = firstName + " " +middleName + " " +lastName;
        else fullName = firstName + " " +lastName;
    }

    @PostUpdate
    public void onPostUpdate() {
        log.info("Updated user: " + firstName);
    }

    @PostLoad
    public void onPostLoad() {}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        this.getUserRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return authorities;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return email;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return active;
    }
}
