package com.seng3150.flightpub.controller;

import com.seng3150.flightpub.repository.PriceRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceController {
    private final PriceRepository priceRepository;

    public PriceController(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @RequestMapping("/getlowprice")
    double getLowPrice(@RequestParam("fNum") String flightNum,
                       @RequestParam("class") String classCode,
                       @RequestParam("date") String date){
        return priceRepository.getLowPrice(flightNum, classCode, date);
    }
}
