/*
    UserController.java class
        - Rest controller that handles all the incoming front-end requests regarding users
        - Password hashing works but will ultimately be re-done on the front-end due to using HTTP
        - Ideally will have an Error handling controller instead of handling errors in each method
 */
package com.seng3150.flightpub.controller;

import com.seng3150.flightpub.repository.GuestUserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
public class GuestUserController {


    // Access to the user database service
    private GuestUserRepository gusetUserRepository ;

    public GuestUserController(GuestUserRepository userNew){
        this.gusetUserRepository = userNew;
    }


    @RequestMapping("/getGuserId")
    @ResponseBody
    int getGuserId(@RequestParam("email") String email,
                               @RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("phoneNumber") String phoneNumber) throws ParseException {

        return gusetUserRepository.addGuestUser(email,firstName, lastName, phoneNumber);
    }




}
