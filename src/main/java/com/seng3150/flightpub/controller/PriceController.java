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
    double getLowestPrice(@RequestParam("fNum") String flightNum,
                          @RequestParam("date") String date){
        return priceRepository.getLowPrice(flightNum, date);
    }
}
