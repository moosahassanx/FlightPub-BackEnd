/*
    GuestUserRepository.java
        - Extends JPARepo and allows access to the DB
*/

package com.seng3150.flightpub.repository;

import com.seng3150.flightpub.models.GuestUser;
import com.seng3150.flightpub.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface GuestUserRepository extends JpaRepository<GuestUser, Long>, JpaSpecificationExecutor<User> {


    @Modifying
    @Query(value = "UPDATE guest_user_account " +
            "SET user_name = ?1, " +
            "first_name = ?2, " +
            "last_name = ?3, " +
            "phone_number = ?4, " +
            "WHERE email_Address = ?1", nativeQuery = true)
    void updateUserDetails(String userName, String firstName, String lastName, String phoneNumber);


    @Query(value = "SELECT * " +
            "FROM user_account " +
            "WHERE user_name = ?1", nativeQuery = true)
    User getDetailsByUserName(String userName);

}
