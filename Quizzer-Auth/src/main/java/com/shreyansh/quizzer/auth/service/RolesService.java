package com.shreyansh.quizzer.auth.service;

import com.shreyansh.quizzer.auth.entity.User;
import com.shreyansh.quizzer.auth.entity.UserRoles;
import com.shreyansh.quizzer.auth.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RolesService {

    @Autowired
    RoleRepository roleRepository;

    public void addNewRole(String roleName) {
        UserRoles userRoles = new UserRoles(roleName);
        roleRepository.save(userRoles);
    }

    public void addRoles(List<UserRoles> roles) {
        roleRepository.saveAll(roles);
    }

    public List<UserRoles> getRoles() {
       return roleRepository.findAll();
    }

    public List<User> getUsersByRoleIdOrName(String roleId){
        List<User> users  = roleRepository.findRoleById(roleId);
        return users;
    }
}
