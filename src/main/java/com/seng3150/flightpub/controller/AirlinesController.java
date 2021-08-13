/*
    DestinationController.java
        - Used to handle all destination requests
 */

package com.seng3150.flightpub.controller;

import com.seng3150.flightpub.models.Airlines;
import com.seng3150.flightpub.repository.AirlinesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class AirlinesController {

    // attributes
    private final AirlinesRepository airlinesRepository;

    // constructor
    public AirlinesController(AirlinesRepository airlinesRepository) {
        this.airlinesRepository = airlinesRepository;
    }

    // Returns a list of all destinations currently FlightPub offers
    @RequestMapping("/getAirlines")
    List<Airlines> findAllAirlines() {

        System.out.println("GETTING ALL AIRLINES");

        return airlinesRepository.findAirlines();
    }

    // promote the user depending on the username passed as a parameter
    @Transactional
    @RequestMapping(value = "/updateSponsorship",
            method = RequestMethod.PUT)
    public ResponseEntity<?> promoteUser(@RequestBody HashMap<String, String> jsonData) {

        /* ========== JSON USAGE EXAMPLE ============
        {
            "airlineCode": "AC",
            "trueOrFalse": "1"
        }
        */

        System.out.println("===========================================================");
        System.out.println("ATTEMPTING TO PROMOTE USER: " + jsonData.get("airlineCode") + " TO " + jsonData.get("trueOrFalse"));

        // converting string to boolean
        int trueOrFalse1 = Integer.parseInt(jsonData.get("trueOrFalse"));
        
        // feeding into repository
        airlinesRepository.changeStatus(jsonData.get("airlineCode"), trueOrFalse1);

        return new ResponseEntity<>("Airline " + jsonData.get("airlineCode") + " sponsorship successfully promoted", HttpStatus.OK);
    }

}
