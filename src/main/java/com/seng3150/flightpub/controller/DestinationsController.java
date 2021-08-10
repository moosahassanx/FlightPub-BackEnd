/*
    DestinationController.java
        - Used to handle all destination requests
 */

package com.seng3150.flightpub.controller;

import com.seng3150.flightpub.models.Destinations;
import com.seng3150.flightpub.repository.DestinationsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class  DestinationsController {

    private final DestinationsRepository destinationsRepository;

    public DestinationsController(DestinationsRepository destinationsRepository) {
        this.destinationsRepository = destinationsRepository;
    }

    // Returns a list of all destinations currently FlightPub offers
    @RequestMapping("/getDestinations")
     List<Destinations> findAllDestinations() {

        return destinationsRepository.findDestinations();
    }

    // Returns a list of all destinations currently FlightPub offers
    @RequestMapping("/getNonCovidDestinations")
    List<Destinations> findAllNonCovidDestinations() {

        return destinationsRepository.findNonCovidDestinations();
    }

    // Returns the top 10 destinations being booked currently
    @RequestMapping("/destinationtrending")
    List<String> findTop10ByTimesBookedOrderByTimesBooked() {

        return destinationsRepository.findDestinationName();
    }

    @RequestMapping("/desCovid")
    @ResponseBody
    int changeStatus(@RequestParam("destCode") String destCode,
                     @RequestParam("trueOrFalse") int trueOrFalse){

        return destinationsRepository.changeStatus(destCode,trueOrFalse);
}
}
