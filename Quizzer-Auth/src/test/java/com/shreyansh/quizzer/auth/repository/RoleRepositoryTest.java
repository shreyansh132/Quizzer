package com.shreyansh.quizzer.auth.repository;

import com.shreyansh.quizzer.auth.entity.UserRoles;
import com.shreyansh.quizzer.auth.service.UserService;
import com.shreyansh.quizzer.auth.util.AuditorAwareImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RoleRepositoryTest {

    @Autowired
    RoleRepository roleRepository;

    @Test
    void addData(){
       // AuditorAwareImpl.user = userService.getUserById("bozF36EZWuRBNzspfPDlRkWb");
        UserRoles userRoles = UserRoles.builder()
                .roleName("Admin").build();
        roleRepository.save(userRoles);
    }

    @Test
    void printData(){
        System.out.println(roleRepository.findAll());
    }

//    @Test
//    void findUsersByRoleId(){
//        List<UserRoles> userRolesList = roleRepository.findUsersByRoleId("yraFZLJV7vP1pXaBsQXasXCS");
//        System.out.println(userRolesList);
//    }
}