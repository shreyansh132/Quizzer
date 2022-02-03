package com.shreyansh.quizzer.auth.repository;

import com.shreyansh.quizzer.auth.entity.User;
import com.shreyansh.quizzer.auth.entity.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    User findByUserIdOrEmail(String id, String email);

    User findByEmail(String username);

    boolean existsByEmail(String email);

    List<User> findByActive(Boolean active);

    @Modifying
    @Transactional
    @Query("update User u set u.password = ?1 where u.email = ?2")
    int updateUserPassword(String password, String email);

    @Query("select u from User u where u.userRoles = ?1")
    List<User> findUsersByRole(UserRoles role);

    @Query("select userId from User u where u.email = ?1")
    String findUsersIdByEmail(String email);

    @Modifying
    @Query("update User u set u.imageUrl = ?1 where u.id = ?2")
    int updateUserImage(String url, Integer id);

    @Modifying
    @Transactional
    @Query("update User u set u.auditRecord.createdById = ?1, u.auditRecord.lastModifiedById = ?2 where u.id = ?3")
    int updateUserCreatedByLastModBy(String createdById, String lastModId, String recId);
}
