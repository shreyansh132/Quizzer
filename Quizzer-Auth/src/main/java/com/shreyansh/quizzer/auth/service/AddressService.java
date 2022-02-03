package com.shreyansh.quizzer.auth.service;

import com.shreyansh.quizzer.auth.entity.Address;
import com.shreyansh.quizzer.auth.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAllAddress() {
       return addressRepository.findAll();
    }
}
