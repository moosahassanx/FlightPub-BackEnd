package com.seng3150.flightpub.controller;

import com.seng3150.flightpub.models.Wishlist;
import com.seng3150.flightpub.repository.DestinationsRepository;
import com.seng3150.flightpub.repository.UserRepository;
import com.seng3150.flightpub.repository.WishlistRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;           // unused but leave this here anyway
import org.springframework.web.bind.annotation.RestController;

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

    @Transactional
    @RequestMapping("/removeWishlistItem")
    ResponseEntity<?> removeWishlistItem(@RequestParam("userName") String user_name,
                                      @RequestParam String countryCode3) {
        long user_id = userRepository.getDetailsByUserName(user_name).getId();
        this.wishlistRepository.removeWishlistItem((int)user_id, countryCode3);

        return new ResponseEntity<>(countryCode3 + " removed from the wishlist.", HttpStatus.OK);
    }
}
