package com.seng3150.flightpub.controller;

import com.seng3150.flightpub.repository.UserAccountBookingRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserAccountBookingController
{
    private final UserAccountBookingRepository uabRepo;

    public UserAccountBookingController(UserAccountBookingRepository uabRepo)
    {
        this.uabRepo = uabRepo;
    }

    @RequestMapping("/getTopVisitedLocations")
    public Map<String, Object> getTopLocations(@RequestParam("userid") int id)
    {
        Map<String, Object> toplocations = new HashMap<>();
        List<String> locations = uabRepo.getAllPreviousBookings(id);

        return toplocations;
    }
}
