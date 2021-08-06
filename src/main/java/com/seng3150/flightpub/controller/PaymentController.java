package com.seng3150.flightpub.controller;

import com.seng3150.flightpub.repository.PaymentRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {
    private final PaymentRepository paymentRepository;

    public PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @RequestMapping("/makeRPayment")
    @ResponseBody
    int makeRPayment(@RequestParam("price") double price,
                     @RequestParam("userId") int userId){
         return paymentRepository.makeRPayment(price, userId);

    }

    @RequestMapping("/makeGPayment")
    @ResponseBody
    int makeGPayment(@RequestParam("price") double price,
                     @RequestParam("guestUserId") int guestUserId){
        return paymentRepository.makeGPayment(price, guestUserId);

    }
}
