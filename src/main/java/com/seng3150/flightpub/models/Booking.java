/*
    Booking.java
        - Models the DB table and maps the contraints via annotations
*/
package com.seng3150.flightpub.models;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "booking")
@IdClass(BookingComposite.class)
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonIgnore
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @OneToOne
    @JoinColumn(name = "guest_user_id", nullable = true)
    private GuestUser guestUser;

    @Id
    @Column(name = "airline_code", nullable = true)
    private String  airline_code;

    @Id
    @Column(name = "flight_number", nullable = false)
    private String flight_number;

    @Column(name = "payment_complete", nullable = false)
    private String payment_complete;

    @ManyToOne
    private Flights flight;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment paymentId;


}
