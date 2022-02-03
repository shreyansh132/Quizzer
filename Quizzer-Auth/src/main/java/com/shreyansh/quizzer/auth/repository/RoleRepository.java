package com.shreyansh.quizzer.auth.repository;

import com.shreyansh.quizzer.auth.entity.User;
import com.shreyansh.quizzer.auth.entity.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<UserRoles,String> {
    UserRoles findRoleByRoleName(String roleName);

    @Query("select users from UserRoles u where u.roleId = ?1 or u.roleName = ?1")
    List<User> findRoleById(String roleIdName);
}
