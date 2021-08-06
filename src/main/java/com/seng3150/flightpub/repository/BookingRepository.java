package com.seng3150.flightpub.repository;

import com.seng3150.flightpub.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface BookingRepository extends JpaRepository<Booking, Long>, JpaSpecificationExecutor<Booking> {



   @Query(value = "INSERT INTO booking " +
            "OUTPUT Inserted.book_id " +
            "(booking.flight_number, booking.payment_complete, booking.payment_id, booking.user_id, booking.guest_user_id, booking.airline_code, booking.flight_airline_code, booking.flight_departure_time, booking.flight_flight_number)" +
            "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9)", nativeQuery = true)
    int newBooking(String flightNumber, String paymentComplete, int paymentId, int userId, int guestUserId, String airlineCode, String flightAirlineCode, String flightDepTime, String flightFlightnumber);


    @Query(value = "INSERT INTO booking " +
            "OUTPUT Inserted.book_id " +
            "(booking.flight_number, booking.payment_complete, booking.payment_id, booking.user_id, booking.airline_code, booking.flight_airline_code, booking.flight_departure_time, booking.flight_flight_number)" +
            "VALUES (?1, ?2, ?3, ?4, ?5, ?6, CONVERT(datetime2, ?7), ?8)", nativeQuery = true)
    int addRejestedBooking(String flightNumber, String paymentComplete, int paymentId, int userId, String airlineCode, String flightAirlineCode, String flightDepTime, String flightFlightnumber);

    @Query(value = "INSERT INTO booking " +
            "OUTPUT Inserted.book_id " +
            "(booking.flight_number, booking.payment_complete, booking.payment_id, booking.guest_user_id, booking.airline_code, booking.flight_airline_code, booking.flight_departure_time, booking.flight_flight_number)" +
            "VALUES (?1, ?2, ?3, ?4, ?5, ?6, CAST(?7 AS DATETIME2), ?8)", nativeQuery = true)
    int makeGBooking(String flightNumber, String paymentComplete, int paymentId, int guestUserId, String airlineCode, String flightAirlineCode, String flightDepTime, String flightFlightnumber);
}
