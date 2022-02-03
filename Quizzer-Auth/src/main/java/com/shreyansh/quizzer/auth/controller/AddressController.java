package com.shreyansh.quizzer.auth.controller;

import com.shreyansh.quizzer.auth.entity.Address;
import com.shreyansh.quizzer.auth.model.ResponseHandler;
import com.shreyansh.quizzer.auth.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllAddress() {
        ResponseHandler responseHandler = new ResponseHandler();
        try {
            List<Address> addressList = addressService.getAllAddress();
            return responseHandler.generateResponse("Address fetched Successfully", HttpStatus.OK, addressList);
        }
        catch (Exception e){
            return responseHandler.generateResponse(e.getMessage(),HttpStatus.BAD_REQUEST,null);
        }
    }
}
