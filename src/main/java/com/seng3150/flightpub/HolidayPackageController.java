/*
    HolidayPackageController.java
        - Used to handle all HolidayPackage requests
 */

package com.seng3150.flightpub.controller;

import com.seng3150.flightpub.models.HolidayPackage;
import com.seng3150.flightpub.repository.HolidayPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HolidayPackageController {

    private final HolidayPackageRepository holidayPackageRepository;

    public HolidayPackageController(HolidayPackageRepository holidayPackageRepository) {
        this.holidayPackageRepository = holidayPackageRepository;
    }

    // Returns a list of all holiday package currently FlightPub offers
    @RequestMapping("/getHolidayPackages")
     List<HolidayPackage> findAllHolidayPackages() {

        return holidayPackageRepository.findHolidayPackages();
    }
    
    // Returns the list of holiday packages targeted at solo travellers
    @RequestMapping("/getSoloHolidayPackages")
    List<HolidayPackage> findAllSoloHolidayPackages() {

        return holidayPackageRepository.findSoloHolidayPackages();
    }

    // Returns the list of holiday packages targeted at family travellers
    @RequestMapping("/getFamilyHolidayPackages")
    List<HolidayPackage> findAllFamilyHolidayPackages() {

        return holidayPackageRepository.findFamilyHolidayPackages();
    }

    // Returns the list of holiday packages targeted at senior travellers
    @RequestMapping("/getSeniorHolidayPackages")
    List<HolidayPackage> findAllSeniorHolidayPackages() {

        return holidayPackageRepository.findSeniorHolidayPackages();
    }

    // Returns the list of holiday packages targeted at business travellers
    @RequestMapping("/getBusinessHolidayPackages")
    List<HolidayPackage> findAllBusinessHolidayPackages() {

        return holidayPackageRepository.findBusinessHolidayPackages();
    }

    // Returns the list of recommended holiday packages
    @RequestMapping("/getRecommendedHolidayPackages")
    List<HolidayPackage> findRecommendedHolidayPackages(@RequestParam("1") String rd1,
                                                        @RequestParam("2") String rd2) {

        return holidayPackageRepository.findRecommendedHolidayPackages(rd1, rd2);
    }

    // Returns the list of holiday packages targeted at business travellers
    @RequestMapping("/getRecommended")
    List<HolidayPackage> findRecommended() {

        return holidayPackageRepository.findRecommended();
    }
}