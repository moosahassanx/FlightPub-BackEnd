/*
    CountryController.java
        - Not used, will be extended to implement the user booking selected flights
 */

package com.seng3150.flightpub.controller;

import com.seng3150.flightpub.repository.AvailabilityRepository;
import com.seng3150.flightpub.repository.BookingRepository;
import com.seng3150.flightpub.repository.FlightsRepository;
import com.seng3150.flightpub.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

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

    @RequestMapping("/makeBooking")
    @ResponseBody
    int addBooking(@RequestParam("fNumber") String flightNumber,
                   @RequestParam("payComp") String paymentComplete,
                   @RequestParam("payId") int paymentId,
                   @RequestParam("uId") int userId,
                   @RequestParam("gUId") int guestUserId,
                   @RequestParam("aCode") String airlineCode,
                   @RequestParam("fACode") String flightAirlineCode,
                   @RequestParam("fDepTime") String flightDepTime,
                   @RequestParam("FFNumber") String flightFlightNumber) {

        return bookingRepository.newBooking(flightNumber, paymentComplete, paymentId, userId, guestUserId, airlineCode, flightDepTime, flightAirlineCode, flightFlightNumber);

    }

    @RequestMapping("/makeRBooking")
    @ResponseBody
    int addRejestedBooking(@RequestParam("fNumber") String flightNumber,
                           @RequestParam("payComp") String paymentComplete,
                           @RequestParam("payId") int paymentId,
                           @RequestParam("uId") int userId,
                           @RequestParam("aCode") String airlineCode,
                           @RequestParam("fACode") String flightAirlineCode,
                           @RequestParam("fDepTime") String flightDepTime,
                           @RequestParam("FFNumber") String flightFlightNumber) {

        return bookingRepository.addRejestedBooking(flightNumber, paymentComplete, paymentId, userId, airlineCode, flightDepTime, flightAirlineCode, flightFlightNumber);

    }

    @RequestMapping("/makeGBooking")
    @ResponseBody
    int makeGBooking(@RequestParam("fNumber") String flightNumber,
                     @RequestParam("payComp") String paymentComplete,
                     @RequestParam("payId") int paymentId ,
                     @RequestParam("gUId") int guestUserId,
                     @RequestParam("aCode") String airlineCode,
                     @RequestParam("fDepTime") String flightDepTime,
                     @RequestParam("fACode") String flightAirlineCode,
                     @RequestParam("FFNumber") String flightFlightNumber) throws ParseException {

        return bookingRepository.makeGBooking(flightNumber, paymentComplete, paymentId, guestUserId, airlineCode, flightDepTime, flightAirlineCode, flightFlightNumber);

    }


}
