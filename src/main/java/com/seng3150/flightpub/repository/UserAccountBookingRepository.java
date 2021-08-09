package com.seng3150.flightpub.repository;

import com.seng3150.flightpub.models.Destinations;
import com.seng3150.flightpub.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserAccountBookingRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>
{
    @Query(value = "SELECT * " +
            "FROM user_account_booking_list " +
            "WHERE user_id = ?1", nativeQuery = true)
    List<String> getAllPreviousBookings(int user_id);
}
