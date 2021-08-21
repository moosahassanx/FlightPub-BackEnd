/*
    DestinationController.java
        - Used to handle all destination requests
 */

package com.seng3150.flightpub.controller;

import com.seng3150.flightpub.models.Destinations;
import com.seng3150.flightpub.repository.DestinationsRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    
    // Returns the top 10 destinations being booked currently
    @RequestMapping("/destinationtrending")
    List<String> findTop10ByTimesBookedOrderByTimesBooked() {

        return destinationsRepository.findDestinationName();
    }

    // updating covid destination to preferred thing
    @Transactional
    @RequestMapping(value = "/desCovid",
            method = RequestMethod.PUT)
    public ResponseEntity<?> promoteUser(@RequestBody HashMap<String, String> jsonData) {

        /* ========== JSON USAGE EXAMPLE ============
        {
            "destCode": "ADL",
            "trueOrFalse": "1"
        }
        */

        // converting string to boolean
        int trueOrFalse1 = Integer.parseInt(jsonData.get("trueOrFalse"));

        System.out.println("================= TRYNNA CHANGE COVID DESTINATION =====================");
        System.out.println("destCode: " + jsonData.get("destCode"));
        System.out.println("TorF: " + trueOrFalse1);

        // feeding into repository
        destinationsRepository.changeStatus(jsonData.get("destCode"), trueOrFalse1);

        return new ResponseEntity<>("Destination covid thing done", HttpStatus.OK);
    }

    @RequestMapping("/trendingDestinations")
     List<Destinations> findTrending() {

        return destinationsRepository.findTrending();
    }

}
