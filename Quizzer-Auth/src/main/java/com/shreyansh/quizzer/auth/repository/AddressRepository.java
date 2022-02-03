package com.shreyansh.quizzer.auth.repository;
import com.shreyansh.quizzer.auth.entity.User;
import com.shreyansh.quizzer.auth.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AddressRepository extends JpaRepository<Address,String> {

    @Transactional
    @Modifying
    @Query("update Address a set a.user = ?1 where a.addressId = ?2")
    int updateAddressWithUserId(User user, String addressId);
}
