package com.seng3150.flightpub.controller;

import com.seng3150.flightpub.models.Booking;
import com.seng3150.flightpub.models.Destinations;
import com.seng3150.flightpub.repository.DestinationsRepository;
import com.seng3150.flightpub.repository.UserHistoryRepository;
import com.seng3150.flightpub.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserHistoryController
{
    private final UserHistoryRepository userHistoryRepository;
    private final UserRepository userRepository;
    private final DestinationsRepository destinationsRepository;

    public UserHistoryController(UserHistoryRepository userHistoryRepository, UserRepository userRepository, DestinationsRepository destinationsRepository)
    {
        this.userHistoryRepository = userHistoryRepository;
        this.userRepository = userRepository;
        this.destinationsRepository = destinationsRepository;
    }

    @RequestMapping("/getUserHistory")
    List<Booking> getUserFlightHistory(@RequestParam("userName") String userName)
    {
        long userId = userRepository.getDetailsByUserName(userName).getId();
        return userHistoryRepository.getUserFlightHistory((int)userId);
    }

    @RequestMapping("/getTop3Destinations")
    List<Destinations> getTop3Destinations()
    {
        return destinationsRepository.getTop3Destinations();
    }
}
