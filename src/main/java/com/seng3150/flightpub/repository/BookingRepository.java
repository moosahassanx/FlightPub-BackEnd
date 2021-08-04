package com.seng3150.flightpub.repository;

import com.seng3150.flightpub.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface BookingRepository extends JpaRepository<Booking, Long>, JpaSpecificationExecutor<Booking> {



    @Query(value = "INSERT INTO booking" +
            "(flight_number, payment_complete, payment_id, user_id, guest_user_id, airline_code, flight_airline_code, flight_departure_time, flight_flight_number)" +
            "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9)", nativeQuery = true)
    void newBooking(String flightNumber, String paymentComplete, String paymentId, String userId, String guestUserId, String airlineCode, String flightAirlineCode, String flightDepTime, String flightFlightnumber);


    @Query(value = "INSERT INTO booking" +
            "(flight_number, payment_complete, payment_id, user_id, airline_code, flight_airline_code, flight_departure_time, flight_flight_number)" +
            "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)", nativeQuery = true)
    void addRejestedBooking(String flightNumber, String paymentComplete, String paymentId, String userId, String airlineCode, String flightAirlineCode, String flightDepTime, String flightFlightnumber);

    @Query(value = "INSERT INTO booking" +
            "(flight_number, payment_complete, payment_id, guest_user_id, airline_code, flight_airline_code, flight_departure_time, flight_flight_number)" +
            "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)", nativeQuery = true)
    void addGuestBooking(String flightNumber, String paymentComplete, String paymentId, String guestUserId, String airlineCode, String flightAirlineCode, String flightDepTime, String flightFlightnumber);
}
