/*
    CountryController.java
        - Not used, will be extended to implement the user booking selected flights
 */

package com.seng3150.flightpub.controller;

import com.seng3150.flightpub.models.User;
import com.seng3150.flightpub.repository.AvailabilityRepository;
import com.seng3150.flightpub.repository.BookingRepository;
import com.seng3150.flightpub.repository.FlightsRepository;
import com.seng3150.flightpub.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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

    /*  Things passed into controller
           - username           - Flight start date
           - flight number      - ticket type       
     */
    // Testing controller method
    @RequestMapping(value = "/bookFlight")
    public ResponseEntity<?> bookFlight(@RequestBody HashMap<String,String> jsonData) {
        // Grab the user entity, flight will be available,
        User user = userRepository.getByUserName(jsonData.get("userName"));
        return null;
    }

}
