package com.seng3150.flightpub.repository;

import com.seng3150.flightpub.models.Availability;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability, String>, JpaSpecificationExecutor<Availability> {
//datediff(day, cast(Availability.departure_time as datetime)
    @Query(value = "SELECT * " +
            "FROM Availability " +
            "WHERE Availability.flight_number = ?2 " +
            "AND datediff(day, Availability.departure_time, ?1) = 0 " +
            "AND Availability.number_available_seats_leg1 >= ?3 " +
            "AND (Availability.number_available_seats_leg2 >= ?3 OR Availability.number_available_seats_leg2 IS NULL) " +
            "AND availability.class_code = ?4", nativeQuery = true)
     List<Availability> findFlightsAvailability(String depTime , String flightNumber, int seats, String classCode);


}