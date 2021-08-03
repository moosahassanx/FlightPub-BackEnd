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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuestUserController {


    // Access to the user database service
    private GuestUserRepository gusetUserRepository ;

    public GuestUserController(GuestUserRepository userNew){
        this.gusetUserRepository = userNew;
    }



    @RequestMapping(value="/addGuestUser",
            method= RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> addGuestUser(@RequestBody MultiValueMap<String, String> formData) {


        String user_name =  formData.getFirst("email");
        String first_name =  formData.getFirst("firstName");
        String last_name =  formData.getFirst("lastName");
        String phone_number = formData.getFirst("phoneNumber");

        gusetUserRepository.save(new GuestUser(user_name, first_name, last_name, phone_number));

        // Returning HTTP status
        return new ResponseEntity<>( HttpStatus.OK);
    }




}
