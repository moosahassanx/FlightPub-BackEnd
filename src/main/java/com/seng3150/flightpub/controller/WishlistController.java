package com.seng3150.flightpub.controller;

import com.seng3150.flightpub.models.Wishlist;
import com.seng3150.flightpub.repository.DestinationsRepository;
import com.seng3150.flightpub.repository.UserRepository;
import com.seng3150.flightpub.repository.WishlistRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class WishlistController {
    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;

    public WishlistController(WishlistRepository wishlistRepository, UserRepository userRepository) {
        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping("/newWishlistItem")
    int addWishlistItem(@RequestParam String user_name,
                        @RequestParam String countryCode3) {
        long user_id = userRepository.getDetailsByUserName(user_name).getId();
        return wishlistRepository.newWishlistItem((int)user_id, countryCode3);
    }

    @RequestMapping("/getWishlist")
    List<Wishlist> getWishlistItems(@RequestParam String user_name) {
        long user_id = userRepository.getDetailsByUserName(user_name).getId();
        return wishlistRepository.getWishlistItems((int)user_id);
    }


    /*@DeleteMapping("/removeWishlistItem")
    void removeWishlistItem(@PathVariable("userName") String user_name,
                                      @PathVariable("countryCode3") String countryCode3) {
        long user_id = userRepository.getDetailsByUserName(user_name).getId();
        this.wishlistRepository.removeWishlistItem((int)user_id, countryCode3);
    }*/
    @Transactional
    @RequestMapping(value = "/removeWishlistItem",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeUser(@RequestBody HashMap<String, String> jsonData) {
        System.out.println("===============================================================");

        long user_id = userRepository.getDetailsByUserName(jsonData.get("userName")).getId();

        System.out.println("userName: " + jsonData.get("userName"));
        System.out.println("countryCode3: " + jsonData.get("countryCode3"));
        System.out.println("user_id: " + (int)user_id);

        wishlistRepository.removeWishlistItem((int)user_id, jsonData.get("countryCode3"));

        return new ResponseEntity<>("Successfully removed", HttpStatus.OK);
    }
}
