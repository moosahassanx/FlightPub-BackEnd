/*
    FlightController.java
        - Rest controller to handle all incoming HTTP requests
*/

package com.seng3150.flightpub.controller;

import com.seng3150.flightpub.enums.EDestinations;
import com.seng3150.flightpub.models.Destinations;
import com.seng3150.flightpub.models.Flights;
import com.seng3150.flightpub.repository.FlightsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class FlightController {

    private final FlightsRepository flightsRepository;

    public FlightController(FlightsRepository flightsRepository) {
        this.flightsRepository = flightsRepository;
    }
    
    // Returns a list of flights representing the users search request
    @RequestMapping("/getflights")
    List<Flights> findAllDestinations(@RequestParam("from") String depart,
                                      @RequestParam("to") String arrive,
                                      @RequestParam("dep") String date) {

        return flightsRepository.findFlights(depart, arrive, date);
    }

    // GET REQUEST
    // URI mapping to get trendingflights - key = destination, value = user selected destination
    // Maps the users selected destination with the EDestination to get the destination code
    // returns list of flights matching the destination code
    @RequestMapping("/trendingflights")
    List<Flights> getTrendingFlightDestinations(@RequestParam("Destination") String destination) {
        // Maps the destination to the destination code for easier database mapping via enum class
        String code = EDestinations.DestinationCodes.fromString(destination);
        return flightsRepository.getTrendingFlights(code, "2020-09-01","2020-09-25");
    }
}
