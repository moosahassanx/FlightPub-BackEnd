/*
    UserController.java class
        - Rest controller that handles all the incoming front-end requests regarding users
        - Password hashing works but will ultimately be re-done on the front-end due to using HTTP
        - Ideally will have an Error handling controller instead of handling errors in each method
 */
package com.seng3150.flightpub.controller;

import com.seng3150.flightpub.models.GuestUser;
import com.seng3150.flightpub.repository.GuestUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuestUserController {


    // Access to the user database service
    private GuestUserRepository gusetUserRepository ;

    public GuestUserController(GuestUserRepository userNew){
        this.gusetUserRepository = userNew;
    }



    @PostMapping(path="/addGuestUser")
    public ResponseEntity<?> addGuestUser(@RequestParam("email") String user_name,
                                          @RequestParam("firstName") String first_name,
                                          @RequestParam("lastName") String last_name,
                                          @RequestParam("phoneNumber") String phone_number) {

        gusetUserRepository.save(new GuestUser(user_name, first_name, last_name, phone_number));

        // Returning HTTP status
        return new ResponseEntity<>( HttpStatus.OK);
    }




}
