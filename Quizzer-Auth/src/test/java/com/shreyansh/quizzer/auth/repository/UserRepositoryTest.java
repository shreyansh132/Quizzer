package com.shreyansh.quizzer.auth.repository;

import com.shreyansh.quizzer.auth.entity.Address;
import com.shreyansh.quizzer.auth.entity.User;
import com.shreyansh.quizzer.auth.entity.UserRoles;
import com.shreyansh.quizzer.auth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
@Slf4j
class UserRepositoryTest {

    @Autowired
   private UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
   private AddressRepository addressRepository;

    @Test
    void saveUser(){
        //AuditorAwareImpl.user = userService.getUserById("GNOmjjiWuDmFgyPxI9UrCUSe");
        Address sasural = Address.builder()
                .city("Sagar")
                //.auditRecord(auditRecord)
                .houseNoBuildingName("886")
                .pinCode("470004")
                .roadNameAreaColony("Ankur Colony Makronia")
                .state("Madhya Pradesh")
                .build();

        Address maika = Address.builder()
                .city("Pune")
                //.auditRecord(auditRecord)
                .houseNoBuildingName("D17")
                .pinCode("411027")
                .roadNameAreaColony("Bora Planet Apartment")
                .state("Maharastra")
                .build();

        UserRoles userRoles = UserRoles.builder()
                .roleName("Admin User 1")
                .build();

        Set<UserRoles> userRolesSet = new HashSet<>();
        userRolesSet.add(userRoles);

        User user = User.builder()
               .firstName("Ashu")
               .middleName("Ramesh")
               .lastName("Katore")
               .isLocked(false)
               .dateOfBirth(LocalDateTime.now())
               .mobile("34567237")
               .active(true)
               .email("ashukatore@gmail.com")
                .userRoles(userRolesSet)
                .addresses(List.of(sasural,maika))
               .imageUrl("")
               .password("123456")
               .build();

//      AuditRecord auditRecord = AuditRecord.builder().build();

        User u = userRepository.save(user);

//        for(int i =0; i < u.getUserAddress().size(); i++){
//            addressRepository.updateAddressWithUserId(u,u.getUserAddress().get(i).getAddressId());
//        }
    }

    @Test
    void showUsers(){
        System.out.println(userRepository.findAll());
    }
}