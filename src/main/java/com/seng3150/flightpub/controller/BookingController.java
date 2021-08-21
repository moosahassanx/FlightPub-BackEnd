/*
    CountryController.java
        - Not used, will be extended to implement the user booking selected flights
 */

package com.seng3150.flightpub.controller;

import com.seng3150.flightpub.models.Booking;
import com.seng3150.flightpub.repository.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
public class BookingController {

    // Booking controller needs access to Price to get price for 1 or total
    // Availability - check if tickets are available, if so minus the number avail
    // flights - Find the flight information

    // Repos for booking controller to access
    // TODO - For implementation create a DTO to lessen the database access and
    //        increase productivity
    private final AvailabilityRepository availabilityRepository;
    private final BookingRepository bookingRepository;
    private final FlightsRepository flightsRepository;
    private final UserRepository userRepository;
    private final DestinationsRepository destinationsRepository;
//    private final GuestUserBookingListRepository guestUserBookingListRepository;
//    private final RegistedUserBookingListRepository registedUserBookingListRepository;

    public BookingController(AvailabilityRepository availabilityRepository,
                             BookingRepository bookingRepository,
                             FlightsRepository flightsRepository,
                             UserRepository userRepository,
                             DestinationsRepository destinationsRepository,
                             GuestUserBookingListRepository guestUserBookingListRepository,
                             RegistedUserBookingListRepository registedUserBookingListRepository) {
        this.availabilityRepository = availabilityRepository;
        this.bookingRepository = bookingRepository;
        this.flightsRepository = flightsRepository;
        this.userRepository = userRepository;
        this.destinationsRepository = destinationsRepository;
//        this.guestUserBookingListRepository = guestUserBookingListRepository;
//        this.registedUserBookingListRepository = registedUserBookingListRepository;
    }

    @RequestMapping("/makeBooking")
    @ResponseBody
    int addBooking(@RequestParam("fNumber") String flightNumber,
                   @RequestParam("payComp") String paymentComplete,
                   @RequestParam("payId") int paymentId,
                   @RequestParam("uId") int userId,
                   @RequestParam("gUId") int guestUserId,
                   @RequestParam("aCode") String airlineCode,
                   @RequestParam("fDepTime") String flightDepTime,
                   @RequestParam("DesCode") String desCode,
                   @RequestParam("classCode") String classCode,
                   @RequestParam("ticketCode") String ticketCode) {


        destinationsRepository.updateTimesBooked(desCode);
        int bookingId = bookingRepository.newBooking(flightNumber, paymentComplete, paymentId, userId, guestUserId, airlineCode, flightDepTime, airlineCode, flightNumber);
//        registedUserBookingListRepository.updateRegistedBookingList(userId, bookingId, airlineCode, flightDepTime, flightNumber);
//        guestUserBookingListRepository.updateGuestBookingList(guestUserId, bookingId, airlineCode, flightDepTime, flightNumber);
        availabilityRepository.updateAvailability(airlineCode, flightNumber, flightDepTime, classCode, ticketCode);
        return bookingId;
    }


    @RequestMapping("/makeRBooking")
    @ResponseBody
    int addRejestedBooking(@RequestParam("fNumber") String flightNumber,
                           @RequestParam("payComp") String paymentComplete,
                           @RequestParam("payId") int paymentId,
                           @RequestParam("uId") int userId,
                           @RequestParam("aCode") String airlineCode,
                           @RequestParam("fDepTime") String flightDepTime,
                           @RequestParam("DesCode") String desCode,
                           @RequestParam("classCode") String classCode,
                           @RequestParam("ticketCode") String ticketCode) {


        destinationsRepository.updateTimesBooked(desCode);
        int bookingId = bookingRepository.addRejestedBooking(flightNumber, paymentComplete, paymentId, userId, airlineCode, flightDepTime, airlineCode, flightNumber);
        //       registedUserBookingListRepository.updateRegistedBookingList(userId, bookingId, airlineCode, flightDepTime, flightNumber);
        userRepository.updateLastLocation(desCode, userId);
        availabilityRepository.updateAvailability(airlineCode, flightNumber, flightDepTime, classCode, ticketCode);
        return bookingId;
    }

    @RequestMapping("/makeGBooking")
    @ResponseBody
    int makeGBooking(@RequestParam("fNumber") String flightNumber,
                     @RequestParam("payComp") String paymentComplete,
                     @RequestParam("payId") int paymentId,
                     @RequestParam("gUId") int guestUserId,
                     @RequestParam("aCode") String airlineCode,
                     @RequestParam("fDepTime") String flightDepTime,
                     @RequestParam("DesCode") String desCode,
                     @RequestParam("classCode") String classCode,
                     @RequestParam("ticketCode") String ticketCode) throws ParseException {

        destinationsRepository.updateTimesBooked(desCode);
        int bookingId = bookingRepository.makeGBooking(flightNumber, paymentComplete, paymentId, guestUserId, airlineCode, flightDepTime, airlineCode, flightNumber);
        //       guestUserBookingListRepository.updateGuestBookingList(guestUserId, bookingId, airlineCode, flightDepTime, flightNumber);
        availabilityRepository.updateAvailability(airlineCode, flightNumber, flightDepTime, classCode, ticketCode);
        return bookingId;
    }

    @RequestMapping("/makeNewBooking")
    int makeNewBooking(@RequestParam("1") String flight_number,
                       @RequestParam("2") String payment_complete,
                       @RequestParam("3") int userId,
                       @RequestParam("4") String airline_code,
                       @RequestParam("5") String flight_departure_time,
                       @RequestParam("6") String flight_airline_code,
                       @RequestParam("7") String flight_flight_number) {
        int bookingId = bookingRepository.makeNewBooking(flight_number, payment_complete, userId, airline_code, flight_departure_time, flight_airline_code, flight_flight_number);
        return bookingId;
    }

    @RequestMapping("/findBookings")
    @ResponseBody
    List<Booking> foundBookings(@RequestParam("userId") String userId) throws ParseException {
        return bookingRepository.foundBookings(userId);
    }
}
