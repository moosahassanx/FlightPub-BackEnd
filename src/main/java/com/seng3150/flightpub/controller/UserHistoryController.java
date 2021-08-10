package com.seng3150.flightpub.controller;

import com.seng3150.flightpub.models.Booking;
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

    public UserHistoryController(UserHistoryRepository userHistoryRepository, UserRepository userRepository)
    {
        this.userHistoryRepository = userHistoryRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping("/getUserHistory")
    List<Booking> getUserFlightHistory(@RequestParam("userName") String userName)
    {
        long userId = userRepository.getDetailsByUserName(userName).getId();
        return userHistoryRepository.getUserFlightHistory((int)userId);
    }
}
