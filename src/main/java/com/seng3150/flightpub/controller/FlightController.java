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

    //getFlexFlights?from=${destFrom}&to=${destTo}&dep1=${depStartDate.toJSON()}&dep2=${depEndDate.toJSON()}
    @RequestMapping("/getFlexFlights")
    List<Flights> findAllDestinations(@RequestParam("from") String depart,
                                      @RequestParam("to") String arrive,
                                      @RequestParam("dep1") String date,
                                      @RequestParam("dep2") String date2){

        return flightsRepository.findFlexFlights(depart, arrive, date, date2);
    }

    @RequestMapping("/getflight")
    List<Flights> getFlight(@RequestParam("1") String airline_code,
                            @RequestParam("2") String destination_code,
                            @RequestParam("3") String date_from,
                            @RequestParam("4") String date_to) {

        return flightsRepository.findFlight(airline_code, destination_code, date_from, date_to);
    }

    @RequestMapping("/findFlightNumber")
    String findFlightNumber(@RequestParam("1") String airline_code,
                            @RequestParam("2") String destination_code,
                            @RequestParam("3") String date_from,
                            @RequestParam("4") String date_to) {

        return flightsRepository.findFlightNumber(airline_code, destination_code, date_from, date_to);
    }
}

/*
    FlightController.java
        - Rest controller to handle all incoming HTTP requests
*/
