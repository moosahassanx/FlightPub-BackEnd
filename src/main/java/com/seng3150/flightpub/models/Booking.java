/*
    Booking.java
        - Models the DB table and maps the contraints via annotations
*/
package com.seng3150.flightpub.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "booking")
@IdClass(BookingComposite.class)
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;

    public Booking(){

    }

    public Booking(String npaymentComplete, String nflightAirlineCode, Date nflightDepTime, String nflightFlightnumber){

        this.paymentComplete = npaymentComplete;
        this.flightAirlineCode = nflightAirlineCode;
        this.flightDepTime = nflightDepTime;
        this.flightFlightnumber = nflightFlightnumber;
    }

    @Id
    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @OneToOne
    @JoinColumn(name = "user_id" )
    private User user;

    @OneToOne
    @JoinColumn(name = "guest_user_id")
    private GuestUser guestUser;

    @Column(name = "payment_complete", nullable = false)
    private String paymentComplete;

    @ManyToOne
    private Flights flight;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment paymentId;

    @Column(name = "flight_airline_code", nullable = false, updatable = false, insertable = false)
    private String flightAirlineCode;

    @Column(name = "flight_departure_time", nullable = false, updatable = false, insertable = false)
    private Date flightDepTime;

    @Column(name = "flight_flight_number", nullable = false, updatable = false, insertable = false)
    private String flightFlightnumber;


}
