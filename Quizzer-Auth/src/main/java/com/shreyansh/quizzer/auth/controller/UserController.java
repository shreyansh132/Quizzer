package com.shreyansh.quizzer.auth.controller;

import com.shreyansh.quizzer.auth.constants.APIConstants;
import com.shreyansh.quizzer.auth.entity.User;
import com.shreyansh.quizzer.auth.entity.UserRoles;
import com.shreyansh.quizzer.auth.exception.CustomException;
import com.shreyansh.quizzer.auth.model.ResponseHandler;
import com.shreyansh.quizzer.auth.service.UserService;
import com.shreyansh.quizzer.auth.util.JwtUtil;
import com.shreyansh.quizzer.auth.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ResponseHandler responseHandler;

    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers(@RequestHeader (name = "Authorization") String authorization) {
        String a[] = authorization.split(" ");

        List<User> users = userService.getAllUsers();
        if(users == null || users.size() == 0) responseHandler.generateResponse(APIConstants.NO_DATA_FOUND, HttpStatus.NOT_FOUND, users);
        return responseHandler.generateResponse("Users fetched Successfully!!!", HttpStatus.OK, users);
    }

    @GetMapping("/{idOrEmail}")
    public ResponseEntity<Object> getUser(
            @RequestHeader (name = "Authorization") String authorization,
            @PathVariable String idEmail) {
            User user = null;
        try{
            user = userService.getUserByIdOrEmail(idEmail);
            if(user == null) new ResponseHandler().generateResponse(APIConstants.NO_DATA_FOUND_FOR_ID, HttpStatus.NOT_FOUND, null);
        }
        catch (Exception e){
            new ResponseHandler().generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
        return new ResponseHandler().generateResponse("User fetched Successfully!!!", HttpStatus.OK, user);
    }

    @GetMapping("/active")
    public ResponseEntity<List<User>> getAllActiveUser(){
        return new ResponseEntity<>(userService.getActiveUsers(),HttpStatus.OK);
    }

    @GetMapping("/usersByRole")
    public ResponseEntity<Object> getUsersByRole(@RequestBody UserRoles roles) {
        ResponseHandler responseHandler = new ResponseHandler();
        try {
            List<User> userList = userService.findUsersByRole(roles);
            return responseHandler.generateResponse("Users fetched Successfully", HttpStatus.OK, userList);
        }
        catch (Exception e){
            return responseHandler.generateResponse(e.getMessage(),HttpStatus.BAD_REQUEST,null);
        }
    }

    @PostMapping("/create")
    public String createAccountForAnotherUser(
            @RequestHeader (name = "Authorization") String authorization,
            @RequestBody UserDto userVO) throws CustomException {

        if(userService.accountExists(userVO.getEmail())){
            throw new CustomException(APIConstants.EMAIL_TAKEN_BY_ANOTHER_USER);
        }
        userService.createAccountForAnotherUser(authorization,userVO);
        return APIConstants.ACCOUNT_CREATION_SUCCESSFUL;
    }

    @PostMapping("/update")
    public String updateAccount(
            @RequestHeader (name = "Authorization") String authorization,
            @RequestBody UserDto userVO) throws CustomException {

        userService.updateAccount(authorization,userVO);
        return APIConstants.ACCOUNT_UPDATE_SUCCESSFUL;
    }
}
