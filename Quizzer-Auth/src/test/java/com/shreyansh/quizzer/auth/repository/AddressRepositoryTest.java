package com.shreyansh.quizzer.auth.repository;

import com.shreyansh.quizzer.auth.entity.User;
import com.shreyansh.quizzer.auth.entity.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class AddressRepositoryTest {

    @Autowired
    AddressRepository addressRepository;

    @Test
    void saveAddress() {
        User user = User.builder()
                .firstName("Ashu")
                .lastName("Jain")
                .isLocked(false)
                .dateOfBirth(LocalDateTime.now())
                .mobile("34567237")
                .email("ashulovesshreyanshkatore@gmail.com")
                .imageUrl("")
                .password("234567")
                .build();

        Address userAddress = Address.builder()
                .user(user)
                .city("Ahamad nagar").pinCode("23456")
                .state("M.H.").houseNoBuildingName("234").roadNameAreaColony("Pune road")
                .build();

        addressRepository.save(userAddress);
    }

    @Test
    void printAddress(){
        System.out.println(addressRepository.findAll());
    }
}