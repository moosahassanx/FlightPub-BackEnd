/*
    AvailabilityController.java
        - Rest controller to handle all incoming HTTP requests
*/

package com.seng3150.flightpub.controller;
import com.seng3150.flightpub.models.Availability;
import com.seng3150.flightpub.models.Flights;
import com.seng3150.flightpub.repository.AvailabilityRepository;
import com.seng3150.flightpub.repository.FlightsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
public class AvailabilityController {


    private final AvailabilityRepository availabilityRepository;

    public AvailabilityController(AvailabilityRepository availabilityRepository) {
        this.availabilityRepository = availabilityRepository;
    }

    // Returns a list of flights representing the users search request based on number of travellers

    @RequestMapping("/getAvailability")
    @ResponseBody
    List<Availability> findAllDestinations(@RequestParam("depTime") String departureTime,
                                           @RequestParam("flightNum") String flightNumber,
                                           @RequestParam("depSeats") int seats) throws ParseException {

//         String date = departureTime;
//         SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
//         SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
//         Date parsedDate = inputFormat.parse(date);
////        String formattedDate = outputFormat.format(parsedDate);
//        System.out.println("here is the new date"+parsedDate+"its here");
        return availabilityRepository.findFlightsAvailability(departureTime,flightNumber, seats);
    }


}
