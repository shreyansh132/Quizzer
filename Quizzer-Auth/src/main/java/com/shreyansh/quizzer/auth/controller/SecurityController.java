package com.shreyansh.quizzer.auth.controller;

import com.shreyansh.quizzer.auth.constants.APIConstants;
import com.shreyansh.quizzer.auth.exception.CustomException;
import com.shreyansh.quizzer.auth.model.AuthRequest;
import com.shreyansh.quizzer.auth.model.LoginResponseHandler;
import com.shreyansh.quizzer.auth.model.ResponseHandler;
import com.shreyansh.quizzer.auth.service.UserService;
import com.shreyansh.quizzer.auth.util.JwtUtil;
import com.shreyansh.quizzer.auth.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
@CrossOrigin("*")
public class SecurityController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/signUp")
    public String createAccount(@RequestBody UserDto userDto) throws CustomException {
        if(userService.accountExists(userDto.getEmail())){
            throw new CustomException(APIConstants.EMAIL_TAKEN_BY_ANOTHER_USER);
        }
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userService.createAccount(userDto);
        return APIConstants.ACCOUNT_CREATION_SUCCESSFUL;
    }

    @PostMapping("/login")
    public ResponseEntity<?> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        ResponseHandler responseHandler = new ResponseHandler();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        }

        catch (BadCredentialsException ex) {
            return LoginResponseHandler.generateResponse("Invalid Username/Password",100);
        }

        catch (LockedException ex) {
            return LoginResponseHandler.generateResponse("Account Locked!!!",101);
        }

        catch (DisabledException ex) {
            return LoginResponseHandler.generateResponse("Account disabled!!!",102);
        }

        catch (Exception ex) {
            ex.printStackTrace();
            return responseHandler.generateResponse("There's an error.. Please try login again or contact your administrator", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }

        return responseHandler.generateResponse("Login Successful", HttpStatus.OK, userService.login(authRequest.getUserName()));
    }

    @PostMapping("/password/{email}")
    public ResponseEntity<?> forgotPassword(@PathVariable String email){
        return userService.sendOTPEmail(email);
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateOtp(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "otp") String otp){
        return userService.validateOtp(email,otp);
    }

    @PutMapping("/changePassword")
    public ResponseEntity<?> changePassword(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password){
        return userService.changePassword(email,password);
    }
}
