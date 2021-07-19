/*
    CountryController.java
        - Not used, will be extended to any country related requests
 */

package com.seng3150.flightpub.controller;

import com.seng3150.flightpub.models.Country;
import com.seng3150.flightpub.repository.CountryRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {

    private final CountryRepository countryRepository;

    public CountryController(CountryRepository countryRepository) {

        this.countryRepository = countryRepository;

    }
}
