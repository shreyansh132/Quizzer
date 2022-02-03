package com.shreyansh.quizzer.auth.controller;

import com.shreyansh.quizzer.auth.entity.User;
import com.shreyansh.quizzer.auth.entity.UserRoles;
import com.shreyansh.quizzer.auth.model.ResponseHandler;
import com.shreyansh.quizzer.auth.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolesController {

    @Autowired
    RolesService rolesService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllRoles() {
        ResponseHandler responseHandler = new ResponseHandler();
        try {
            List<UserRoles> userRolesList = rolesService.getRoles();
            return responseHandler.generateResponse("Roles fetched Successfully", HttpStatus.OK, userRolesList);
        }
        catch (Exception e){
            return responseHandler.generateResponse(e.getMessage(),HttpStatus.BAD_REQUEST,null);
        }
    }

    @GetMapping("usersByRole/{roleIdOrName}")
    public ResponseEntity<Object> getUsersByRoleId(@PathVariable String roleIdOrName){
        ResponseHandler responseHandler = new ResponseHandler();
        try {
            List<User> users = rolesService.getUsersByRoleIdOrName(roleIdOrName);
            if(users.size() == 0) return responseHandler.generateResponse("No data found for : " + roleIdOrName, HttpStatus.NOT_FOUND,users);
            return responseHandler.generateResponse("Roles fetched Successfully", HttpStatus.OK,users);
        }
        catch (Exception e){
            return responseHandler.generateResponse(e.getMessage() ,HttpStatus.BAD_REQUEST,null);
        }
    }

    @GetMapping("/add")
    public ResponseEntity<Object> addRoles(@RequestBody List<UserRoles> roleNames){
        ResponseHandler responseHandler = new ResponseHandler();
        try {
            rolesService.addRoles(roleNames);
            return responseHandler.generateResponse("Roles created Successfully", HttpStatus.CREATED,null);
        }
        catch (Exception e){
            return responseHandler.generateResponse(e.getMessage() ,HttpStatus.BAD_REQUEST,null);
        }
    }
}