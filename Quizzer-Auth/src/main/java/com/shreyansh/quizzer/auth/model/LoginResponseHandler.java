package com.shreyansh.quizzer.auth.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LoginResponseHandler {
    public static ResponseEntity<Object> generateResponse(
            String message, int code) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("code", code);
        return new ResponseEntity<Object>(map,HttpStatus.valueOf(400));
    }
}
