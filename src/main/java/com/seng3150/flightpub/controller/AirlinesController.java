/*
    DestinationController.java
        - Used to handle all destination requests
 */

package com.seng3150.flightpub.controller;

import com.seng3150.flightpub.models.Airlines;
import com.seng3150.flightpub.repository.AirlinesRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AirlinesController {

    private final AirlinesRepository airlinesRepository;

    public AirlinesController(AirlinesRepository airlinesRepository) {
        this.airlinesRepository = airlinesRepository;
    }

    // Returns a list of all destinations currently FlightPub offers
    @RequestMapping("/getAirlines")
    List<Airlines> findAllAirlines() {

        System.out.println("GETTING ALL AIRLINES");

        return airlinesRepository.findAirlines();
    }
}
