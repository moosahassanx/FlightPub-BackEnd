package com.seng3150.flightpub.repository;

import com.seng3150.flightpub.models.Booking;
import com.seng3150.flightpub.models.Destinations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserHistoryRepository extends JpaRepository<Booking, Long>, JpaSpecificationExecutor<Booking>
{
    @Query(value = "SELECT * FROM booking WHERE user_id = ?1", nativeQuery = true)
    List<Booking> getUserFlightHistory(int user_id);
}