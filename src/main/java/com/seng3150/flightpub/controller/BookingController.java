/*
    CountryController.java
        - Not used, will be extended to implement the user booking selected flights
 */

package com.seng3150.flightpub.controller;

import com.seng3150.flightpub.models.Booking;
import com.seng3150.flightpub.repository.AvailabilityRepository;
import com.seng3150.flightpub.repository.BookingRepository;
import com.seng3150.flightpub.repository.FlightsRepository;
import com.seng3150.flightpub.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class BookingController {

    // Booking controller needs access to Price to get price for 1 or total
    // Availability - check if tickets are available, if so minus the number avail
    // flights - Find the flight information

    // Repos for booking controller to access
    // TODO - For implementation create a DTO to lessen the database access and
    //        increase productivity
    private final AvailabilityRepository availabilityRepository;
    private final BookingRepository bookingRepository;
    private final FlightsRepository flightsRepository;
    private final UserRepository userRepository;

    public BookingController(AvailabilityRepository availabilityRepository,
                             BookingRepository bookingRepository,
                             FlightsRepository flightsRepository,
                             UserRepository userRepository) {
        this.availabilityRepository = availabilityRepository;
        this.bookingRepository = bookingRepository;
        this.flightsRepository = flightsRepository;
        this.userRepository = userRepository;
    }

    @PostMapping(path="/addBooking")
    public ResponseEntity<?> addBooking(@RequestParam("fNumber") String flightNumber,
                                        @RequestParam("payComp") String paymentComplete,
                                        @RequestParam("payId") String paymentId,
                                        @RequestParam("uId") String userId,
                                        @RequestParam("gUId") String guestUserId,
                                        @RequestParam("aCode") String airlineCode,
                                        @RequestParam("fACode") String flightAirlineCode,
                                        @RequestParam("fDepTime") String flightDepTime,
                                        @RequestParam("ffnumber") String flightFlightnumber) {


        bookingRepository.newBooking(flightNumber, paymentComplete, paymentId, userId, guestUserId, airlineCode, flightAirlineCode, flightDepTime, flightFlightnumber);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PostMapping(path="/addRejestedBooking")
    public ResponseEntity<?> addRejestedBooking(@RequestParam("fNumber") String flightNumber,
                                        @RequestParam("payComp") String paymentComplete,
                                        @RequestParam("payId") String paymentId,
                                        @RequestParam("uId") String userId,
                                        @RequestParam("aCode") String airlineCode,
                                        @RequestParam("fACode") String flightAirlineCode,
                                        @RequestParam("fDepTime") String flightDepTime,
                                        @RequestParam("ffnumber") String flightFlightnumber) {


        bookingRepository.addRejestedBooking(flightNumber, paymentComplete, paymentId, userId, airlineCode, flightAirlineCode, flightDepTime, flightFlightnumber);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PostMapping(path="/addGuestBooking")
    public ResponseEntity<?> addGuestBooking(@RequestParam("fNumber") String flightNumber,
                                        @RequestParam("payComp") String paymentComplete,
                                        @RequestParam("payId") String paymentId,
                                        @RequestParam("gUId") String guestUserId,
                                        @RequestParam("aCode") String airlineCode,
                                        @RequestParam("fACode") String flightAirlineCode,
                                        @RequestParam("fDepTime") String flightDepTime,
                                        @RequestParam("ffnumber") String flightFlightnumber) {


        bookingRepository.addGuestBooking(flightNumber, paymentComplete, paymentId, guestUserId, airlineCode, flightAirlineCode, flightDepTime, flightFlightnumber);
        return new ResponseEntity<>( HttpStatus.OK);
    }


}
