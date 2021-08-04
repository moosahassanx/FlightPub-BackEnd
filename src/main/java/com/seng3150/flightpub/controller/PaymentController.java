package com.seng3150.flightpub.controller;

import com.seng3150.flightpub.repository.PaymentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    private final PaymentRepository paymentRepository;

    public PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @PostMapping(path="/makeRPayment")
    public void makeRPayment(@RequestParam("price") double price,
                                          @RequestParam("userId") int userId){
         paymentRepository.makeRPayment(price, userId);

    }

    @PostMapping(path="/makeGPayment")
    public void makeGPayment(@RequestParam("price") double price,
                                          @RequestParam("guestUserId") int guestUserId){
        paymentRepository.makeGPayment(price, guestUserId);

    }
}
